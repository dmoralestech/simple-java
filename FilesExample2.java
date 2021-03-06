import PrinterFileParser.SetCommandParser;
import PrinterFileParser.ValidateCommandLine;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * <p>
 *
 * </p>
 *
 * <p>
 *
 * </p>
 * <p>
 *
 *
 * </p>
 * @return
 */
public class FilesExample2 {

    //Assumption: Source file is valid format..

    //TODO: comments
    //TODO: logging
    //TODO: test
    // if I don't change anything in the options,  source file = dest file
    // keys and values are not case-sensitive

    //TODO: add StringUtils
    //TODO: refactoring
    //TODO: look at sed

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

    public static void main(String[] args) throws Exception {

//        test1("res/sample2.pjl", "res/sample2_out.pjl");
        // -source="sss" -destination="ddd" -addNew=true -key1="v1" -key2="v2"

        ValidateCommandLine commandLine = new ValidateCommandLine(args);
        commandLine.parse();

        Map<String, String> newOptionsMap = new HashMap<>();
        newOptionsMap.put("userid", "\"1\"");
        newOptionsMap.put("rendermode", "BLACKWHITE GREYSCALE");
        newOptionsMap.put("HOSTPORTNAME", "\"0.19.20.0\"");
        newOptionsMap.put("bannerpageprint", "COLOR");
        newOptionsMap.put("authenticationusernamecharset", "369");

        newOptionsMap = commandLine.getOptionArgs();
        String sourceFile = commandLine.getSourceFullPath();
        String destinationFile = commandLine.getDestFullPath();

//        testPJLFile("res/sample3.pjl", "res/sample3_out.pjl");
        //processPJLFile(sourceFile, destinationFile, newOptionsMap);
        processPJLFile("res/sample2.pjl", "res/sample2_out.pjl", newOptionsMap);

    }

    /**
     * Replace multiple white-spaces with a single white-space in a string
     *
     * @param input - the string with multiple white-spaces
     * @return String
     */
    private static String cleanUpLine(String input) {
        input = input.replaceAll("\\s+", " ");
        return input;

    }

    /**
     * Opens up a PJL file, updates the options, and saves it in a new file. If the destination file exists, it will be deleted and creates a new one.
     *
     * @param sourceFile - source PJL file
     * @param destFile - destination/target file
     * @param newOptionsMap - a map of options that the user wants to update
     * @return boolean - true if successful
     */
    public static boolean processPJLFile(String sourceFile, String destFile, Map<String, String> newOptionsMap) throws Exception {

        Path path = Paths.get(sourceFile);
        Path pathOut = Paths.get(destFile);

        if (pathOut.toFile().exists()) {
            pathOut.toFile().delete();
        }

        try (RandomAccessFile in = new RandomAccessFile(path.toFile().getAbsolutePath(), "r");
             RandomAccessFile out = new RandomAccessFile(pathOut.toFile().getAbsolutePath(), "rw")) {
            processPJLContents(in, out, newOptionsMap);
        }

        return true;

    }

    /**
     * Reads the file byte by byte and if it finds an @-sign it will try to process the PJL metadata line
     *
     * @param in - source file object
     * @param out - destination file object
     * @param newOptionsMap - a map of options that the user wants to update
     * @return
     */
    private static void processPJLContents(RandomAccessFile in, RandomAccessFile out, Map<String, String> newOptionsMap) throws Exception {
        int i;
        while ((i = in.read()) >= 0) {
            if (i == AT_SIGN) {
                processPJLStatement(in, out, newOptionsMap);
            } else {
                out.write(Character.valueOf((char) i));
            }
        }
    }

    private static void processPJLStatement(RandomAccessFile in, RandomAccessFile out, Map<String, String> newOptionsMap) throws Exception {
        int i;
        StringBuilder line = new StringBuilder();
        line.append(AT_SIGN_CHAR);

        byte[] buffer = new byte[3];
        i = in.read(buffer);
        String temp = new String(buffer, "UTF-8");
        if (temp.equalsIgnoreCase(PJL)) {
            line.append(temp);
            while ((i = in.read()) != 10) {
                char c = (char) i;
                line.append(Character.valueOf(c));
            }
            if (i == LINE_FEED) {
                line.append(LINE_FEED_CHAR);
            }

            if (cleanUpLine(line.toString()).startsWith(PJL_SET)) {
                processPjlSETStatement(out, newOptionsMap, line.toString());
            } else {
                writeLineToFile(line.toString(), out);
            }

        } else {
            writeLineToFile(line.toString(), out);
            out.write(buffer);
        }
    }

    private static void writeLineToFile(String input, RandomAccessFile out) throws IOException {
        for (byte b : input.getBytes()) {
            out.write(b);
        }
    }

    private static void processPjlSETStatement(RandomAccessFile out, Map<String, String> newOptionsMap, final String line) throws IOException {

        String newStatement = cleanUpLine(line);
        int equalsSignPos = newStatement.indexOf('=');
        if (equalsSignPos <= 0) {
            return;
        }

        SetCommandParser parser = new SetCommandParser(newStatement);
        parser.parse();
        String commandModifierClause = parser.getCommandModifierClause();
        String optionName = parser.getOptionName();
        String optionNameValue = parser.getOptionNameValue();

        String optionNameNewValue = getNewValue(newOptionsMap, optionName, optionNameValue);
        String newSetStatement = createNewSetStatement(optionName, optionNameNewValue, commandModifierClause);
        System.out.println(line);
        System.out.println(newSetStatement);
        writeLineToFile(newSetStatement, out);
        if (line.endsWith("\r\n")) {
            out.write(CARRIAGE_RETURN);
            out.write(LINE_FEED_CHAR);
        } else if (line.endsWith("\n")) {
            out.write(LINE_FEED_CHAR);
        }

    }


    private static String createNewSetStatement(String optionName, String optionNameValue, String commandModifierClause) {
        StringBuilder newSetStatement = new StringBuilder();
        newSetStatement.append(PJL_SET);
        if (commandModifierClause != null) {
            newSetStatement.append(commandModifierClause);
            newSetStatement.append(SPACE);
        }
        if (optionName != null) {
            newSetStatement.append(optionName);
            newSetStatement.append(SPACE);
        }
        if (optionNameValue != null) {
            newSetStatement.append(EQUALS_OP);
            newSetStatement.append(SPACE);
            newSetStatement.append(optionNameValue);
        }
        return newSetStatement.toString();
    }

    private static String getNewValue(Map<String, String> newOptionsMap, String optionName, String optionNameValue) {
        String newOptionNameValue = null;
        if (optionName != null) {
            if (newOptionsMap.get(optionName.toUpperCase()) != null) {
                newOptionNameValue = newOptionsMap.get(optionName);
            } else {
                newOptionNameValue = optionNameValue;
            }
        }
        return newOptionNameValue;
    }

    public static boolean testPJLFile(String sourceFile, String destFile) throws Exception {

        Path path = Paths.get(sourceFile);
        Path pathOut = Paths.get(destFile);

        try (RandomAccessFile in = new RandomAccessFile(path.toFile().getAbsolutePath(), "r");
             RandomAccessFile out = new RandomAccessFile(pathOut.toFile().getAbsolutePath(), "rw")) {
            testFileIfMatch(in, out);
        }

        return true;

    }

    private static void testFileIfMatch(RandomAccessFile in1, RandomAccessFile in2) throws IOException {
        int i1 = 0, i2 = 0;
        while (true && (i1 >= 0)) {
            i1 = in1.read();
            i2 = in2.read();

            if (i1 != i2) {
                break;
            }

        }

        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        System.out.println("i1 file pos= " + in1.getFilePointer());
        System.out.println("i2 file pos = " + in2.getFilePointer());
    }

}
