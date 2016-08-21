import PrinterFileParser.SetCommandParser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class FilesExample2 {

    //TODO: logging
    //TODO: test
    // if I don't change anything in the options,  source file = dest file
    // keys and values are not case-sensitive
    //TODO: comments

    final static String PJL_SET = "@PJL SET ";
    final static String PJL = "PJL";
    final static String AT_SIGN_CHAR = "@";
    final static char LINE_FEED_CHAR = '\n';
    final static char CARRIAGE_RETURN_CHAR = '\r';
    final static String EQUALS_OP = " = ";
    final static int CARRIAGE_RETURN = 13;
    final static int LINE_FEED = 10;
    final static int AT_SIGN = 64;


    public static void main(String[] args) throws Exception {


//        test1("res/sample2.pjl", "res/sample2_out.pjl");
        // -source="sss" -destination="ddd" -addNew=true -key1="v1" -key2="v2"

//        ValidateCommandLine commandLine = new ValidateCommandLine(args);
//        commandLine.parse();

        Map<String, String> newOptionsMap = new HashMap<>();
        newOptionsMap.put("USERID", "\"12345\"");
        newOptionsMap.put("RENDERMODE", "BLACKWHITE GREYSCALE");
        newOptionsMap.put("HOSTPORTNAME", "\"0.19.20.0\"");
        newOptionsMap.put("BANNERPAGEPRINT", "COLOR");
        newOptionsMap.put("NEW_OPTION2", "GREY");

//        testPJLFile("res/sample3.pjl", "res/sample3_out.pjl");
        processPJLFile("res/sample2.pjl", "res/sample2_out.pjl", newOptionsMap);

    }

    private static String cleanUpLine(String input) {
        //String str = "@PJL    SET     DATE     = \"2013/09/     05\"";
        input = input.replaceAll("\\s+", " ");
        return input;

    }

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

        byte[] b = new byte[3];
        i = in.read(b);
        String temp = new String(b, "UTF-8");
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
                handlePjlSetStatement(out, newOptionsMap, line.toString());
            } else {
                writeLineToFile(line.toString(), out);
            }

        } else {
            writeLineToFile(line.toString(), out);
            out.write(b);
        }
    }

    private static void writeLineToFile(String input, RandomAccessFile out) throws IOException {
        for (byte b : input.getBytes()) {
            out.write(b);
        }
    }

    private static void handlePjlSetStatement(RandomAccessFile out, Map<String, String> newOptionsMap, final String line) throws IOException {

        String newStatement = cleanUpLine(line);
        int equalsSignPos = newStatement.indexOf('=');
        if (equalsSignPos <= 0) {
            return;
        }

//        String optionName = getSetKey(newStatement, equalsSignPos);
//        String optionNameValue = newStatement.substring(equalsSignPos + 1).trim();
        SetCommandParser parser = new SetCommandParser(newStatement);
        String commandModifier = parser.getCommandModifier();
        String commandModifierValue = parser.getCommandModifierValue();
        String optionName = parser.getOptionName();
        String optionNameValue = parser.getOptionNameValue();
        System.out.println("option name: " + optionName);

        String newValue = getNewValue(newOptionsMap, optionName, optionNameValue);
        String newSetStatement = createNewSetStatement(optionName, newValue);
        System.out.println(newSetStatement);
        writeLineToFile(newSetStatement, out);
        if (line.endsWith("\r\n")) {
            out.write(CARRIAGE_RETURN);
            out.write(LINE_FEED_CHAR);
        } else if (line.endsWith("\n")) {
            out.write(LINE_FEED_CHAR);
        }
    }

    private static void parseSetStatement(String input) {
        String[] tokens = input.split(" ");
        String commandModifier = null;
        String commandModifierValue = null;
        String optionName = null;
        String optionNameValue = null;





    }

    private static String getSetKey(String newStatement, int equalsSignPos) {
        // @PJL SET   LPARAM:ABC BANNERPAGEPRINT=OFF
        // @PJL SET   LPARAM : ABC BANNERPAGEPRINT = OFF
        String key = newStatement.substring(PJL_SET.length(), equalsSignPos).trim();
        return key;
    }

    private static String createNewSetStatement(String key, String newValue) {
        StringBuilder newSetStatement = new StringBuilder(PJL_SET.length() + key.length() + newValue.length() + EQUALS_OP.length());
        newSetStatement.append(PJL_SET);
        newSetStatement.append(key);
        newSetStatement.append(EQUALS_OP);
        newSetStatement.append(newValue);
        return newSetStatement.toString();
    }

    private static String getNewValue(Map<String, String> newOptionsMap, String key, String value) {
        String newValue;
        if (newOptionsMap.get(key.toUpperCase()) != null) {
            newValue = newOptionsMap.get(key);
            System.out.println("value: " + newOptionsMap.get(key));
        } else {
            newValue = value;
            System.out.println("value: " + value);
        }
        return newValue;
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
