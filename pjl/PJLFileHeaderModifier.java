package pjl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


/**
 * <p>
 * This class is very specific with its responsibility.
 * It only updates the option name value in the @PJL SET command.
 * It can update any option name(not just USERID) that was passed in the command-line arguments,
 * as long as it exists in the metadata.
 * It also supports multiple jobs inside a PJL file. It will update all option names that exists for each job.
 * </p>
 * <p>
 * <p>
 * How it does it is it looks for "@PJL SET" in the file. Once found it tries to get that data line
 * and replaces the option name value.
 * <p>
 * <p>
 * USAGE:
 * command line parameters example:
 * -SOURCE="<source-file-full-path>" -DESTINATION="<desination_file-full-path>"  [Option=NewValue] ...
 *  example:
 * -SOURCE="C:\\sample1.pjl" -DESTINATION="C:\\sample1_new.pjl"  -USERID="\"1234\"" -OPTIONNAME1="OptionNameValue1" -OPTIONNAME2=OptionNameValue2
 * <p>
 * SOURCE: Source PJL file (full path). It assumes that it's a valid PJL File
 * DESTINATION: The full path where the new file will be saved.
 * OPTIONNAME - The option name you want to change
 * OptionNameValue - The new value you want to update with.
 * <p>
 * - Option names are not case-sensitive: UserId equals USERID
 * - There shouldn't be any spaces between the "=" sign ( Bad: -USERID = "\"1234\""   Good: -USERID="\"1234\""
 * - If you want to use double-quotes or backslash in the option name value, you have to escape it with a \, eg  "\"1234\""
 * <p>
 * <p>
 * <p>
 * <p>
 * Notes:
 * <p>
 * - I used a RandomAccessFile because I can treat the file as binary and have complete control over the file.
 * Plus RandomAccessFile should be able to handle large files(ie it doesn't load the file in memory)
 * <p>
 * - Before working on this project I realise that I think I can solve this with the sed unix command.
 * But I think the purpose of this assessment is to know how I tackle this in Java so I went on this
 * path instead :)
 * <p>
 * - Also this task is quite specific so I tried to make my solution as simple as possible.
 * <p>
 * - Trade-offs: I decided to read the file byte by byte. But if it requires performance, I can probably load
 * the file 8192 bytes at a time in memory and process it accordingly.
 */
public class PJLFileHeaderModifier {
    final static Logger log = Logger.getLogger(PJLFileHeaderModifier.class);

    //TODO: unit test
    final static String PJL_SET = "@PJL SET ";
    final static String PJL = "PJL";
    final static String AT_SIGN_CHAR = "@";
    final static char LINE_FEED_CHAR = '\n';
    final static char CARRIAGE_RETURN_CHAR = '\r';
    final static String SPACE = " ";
    final static String EQUALS_OP = "=";
    final static int CARRIAGE_RETURN = 13;
    final static int LINE_FEED = 10;
    final static int AT_SIGN = 64;

    // -SOURCE="C:\\java\\printstream\\src\\test\\samples\\sample1.pjl" -DESTINATION="C:\\java\\printstream\\src\\test\\samples\\sample1_out.pjl"  -USERID="\"D\""

    /**
     * Opens up a PJL file, updates the options, and saves it in a new file. If the destination file exists, it will be deleted and creates a new one.
     *
     * @param sourceFile    - source PJL file
     * @param destFile      - destination/target file
     * @param newOptionsMap - a map of options that the user wants to update
     * @return boolean - true if successful
     */
    public boolean processPJLFile(final String sourceFile, final String destFile, final Map<String, String> newOptionsMap) throws Exception {

        final Path pathSource = Paths.get(sourceFile);
        final Path pathDestination = Paths.get(destFile);

        if (pathDestination.toFile().exists()) {
            pathDestination.toFile().delete();
        }

        try (final RandomAccessFile in = new RandomAccessFile(pathSource.toFile().getAbsolutePath(), "r");
             final RandomAccessFile out = new RandomAccessFile(pathDestination.toFile().getAbsolutePath(), "rw")) {
            processPJLContents(in, out, newOptionsMap);
        }

        return true;

    }

    /**
     * Reads the file byte by byte and if it finds an @-sign it will try to process the PJL metadata line
     *
     * @param in            - source file object
     * @param out           - destination file object
     * @param newOptionsMap - a map of options that the user wants to update
     * @return
     */
    private void processPJLContents(final RandomAccessFile in, final RandomAccessFile out, final Map<String, String> newOptionsMap) throws Exception {
        int i;
        while ((i = in.read()) >= 0) {
            if (i == AT_SIGN) {
                processPJLStatement(in, out, newOptionsMap);
            } else {
                out.write(Character.valueOf((char) i));
            }
        }
    }

    /**
     * handles a PJL metadata command data line
     *
     * @param in            - source file object
     * @param out           - destination file object
     * @param newOptionsMap - a map of options that the user wants to update
     * @return
     */
    private void processPJLStatement(final RandomAccessFile in, final RandomAccessFile out, final Map<String, String> newOptionsMap) throws Exception {
        int i;
        StringBuilder line = new StringBuilder();
        line.append(AT_SIGN_CHAR);

        byte[] buffer = new byte[3];
        i = in.read(buffer);
        String pjlContainer = new String(buffer, "UTF-8");
        if (pjlContainer.equalsIgnoreCase(PJL)) {
            line.append(pjlContainer);
            while ((i = in.read()) != 10) {
                char c = (char) i;
                line.append(Character.valueOf(c));
            }
            if (i == LINE_FEED) {
                line.append(LINE_FEED_CHAR);
            }

            if (cleanUpWhiteSpaces(line.toString()).startsWith(PJL_SET)) {
                processPjlSETStatement(out, newOptionsMap, line.toString());
            } else {
                writeLineToFile(line.toString(), out);
            }

        } else {
            writeLineToFile(line.toString(), out);
            out.write(buffer);
        }
    }

    /**
     * handles a PJL metadata SET command data line
     *
     * @param out           - destination file object
     * @param newOptionsMap - a map of options that the user wants to update
     * @param dataLine      - SET command data line
     * @return
     */
    private void processPjlSETStatement(final RandomAccessFile out, final Map<String, String> newOptionsMap, final String dataLine) throws IOException {
        final String newStatement = cleanUpWhiteSpaces(dataLine);
        final int equalsSignPos = newStatement.indexOf('=');
        if (equalsSignPos <= 0) {
            return;
        }

        SetCommandParser parser = new SetCommandParser(newStatement);
        parser.parse();

        final String newSetStatement = getNewSetStatement(newOptionsMap, parser);

        System.out.println(dataLine);
        System.out.println(newSetStatement);
        writeLineToFile(newSetStatement, out);
        if (dataLine.endsWith("\r\n")) {
            out.write(CARRIAGE_RETURN);
            out.write(LINE_FEED_CHAR);
        } else if (dataLine.endsWith("\n")) {
            out.write(LINE_FEED_CHAR);
        }

    }

    /**
     * create a PJL SET statement
     *
     * @param newOptionsMap - a map of options that the user wants to update
     * @param parser        - SetCommandParser object that contains option name, option value and command modifier
     * @return - creates a new set statement
     */
    private String getNewSetStatement(Map<String, String> newOptionsMap, SetCommandParser parser) {
        final String commandModifierClause = parser.getCommandModifierClause();
        final String optionName = parser.getOptionName();
        final String optionNameValue = parser.getOptionNameValue();

        final String optionNameNewValue = getNewValue(newOptionsMap, optionName, optionNameValue);
        return createNewSetStatement(optionName, optionNameNewValue, commandModifierClause);
    }

    /**
     * creates a new PJL SET Command data line
     *
     * @param optionName            - option name
     * @param optionNameValue       - option name value
     * @param commandModifierClause - command modifier part
     * @return
     */
    public String createNewSetStatement(final String optionName, final String optionNameValue, final String commandModifierClause) {
        final StringBuilder newSetStatement = new StringBuilder();
        newSetStatement.append(PJL_SET);
        if (StringUtils.isNotEmpty(commandModifierClause)) {
            newSetStatement.append(commandModifierClause);
            newSetStatement.append(SPACE);
        }
        if (StringUtils.isNotEmpty(optionName)) {
            newSetStatement.append(optionName);
            newSetStatement.append(SPACE);
        }
        if (StringUtils.isNotEmpty(optionNameValue)) {
            newSetStatement.append(EQUALS_OP);
            newSetStatement.append(SPACE);
            newSetStatement.append(optionNameValue);
        }
        return newSetStatement.toString();
    }

    /**
     * Returns the new option name value from the newOptionsMap. If it doesn't exist in the map then it returns the original option name value
     *
     * @param newOptionsMap           - options map from command line arguments
     * @param optionName              - option name
     * @param originalOptionNameValue - option name value
     * @return String
     */
    private String getNewValue(final Map<String, String> newOptionsMap, final String optionName, final String originalOptionNameValue) {
        String newOptionNameValue = null;
        if (StringUtils.isNotEmpty(optionName)) {
            if (newOptionsMap.get(optionName.toUpperCase()) != null) {
                log.info("Option name found: " + optionName);
                newOptionNameValue = newOptionsMap.get(optionName);
                log.info("Updating option name value: " + newOptionNameValue);
            } else {
                newOptionNameValue = originalOptionNameValue;
            }
        }
        return newOptionNameValue;
    }

}
