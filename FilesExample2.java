import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    final static String AT_SIGN_CHAR = "@";
    final static char ESC_CHAR = '\u001B';
    final static String EQUALS_OP = " = ";
    final static int CARRIAGE_RETURN = 13;
    final static int LINE_FEED = 10;
    final static int AT_SIGN = 64;
    final static int P_CHAR = 80;


    public static void main(String[] args) throws Exception {
//        test1("res/sample2.pjl", "res/sample2_out.pjl");
        // -source="sss" -destination="ddd" -addNew=true -key1="v1" -key2="v2"

//        ValidateCommandLine commandLine = new ValidateCommandLine(args);
//        commandLine.parse();

        Map<String, String> newOptionsMap = new HashMap<>();
//        newOptionsMap.put("USERID", "A");
//        newOptionsMap.put("RENDERMODE", "BLACKWHITE GREYSCALE");
//        newOptionsMap.put("HOSTPORTNAME", "\"0.19.20.0\"");
//        newOptionsMap.put("NEW_OPTION", "COLOR");
//        newOptionsMap.put("NEW_OPTION2", "GREY");

//        testPJLFile("res/sample3.pjl", "res/sample3_out.pjl");
        processPJLFile("res/sample2.pjl", "res/sample2_out.pjl", newOptionsMap);

    }

    private static String cleanUpLine(String input) throws Exception {
        //String str = "@PJL    SET     DATE     = \"2013/09/     05\"";
        input = input.replaceAll("\\s+", " ");
//        input = input.substring(0, input.indexOf("=")).replaceAll("\\s+", " ");
//        System.out.println("str = " + input);
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
            processSample(in, out);

            // I could check for duplicates
            // I could save all the options to a separate map
///            processHeader(in, out);
///            processMetadata(in, out, newOptionsMap);
///            processPostScriptBlock(in, out, in.getFilePointer());

        }

        return true;

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

    private static void writeLineToFile(String input, RandomAccessFile out) throws IOException {
        for (byte b : input.getBytes()) {
            out.write(b);
        }
    }

    private static void writeLineToFile(String input, FileOutputStream out) throws IOException {
        for (byte b : input.getBytes()) {
            out.write(b);
        }

        if (input.startsWith("@") || input.indexOf(Character.valueOf(ESC_CHAR)) == 0) {
            out.write(CARRIAGE_RETURN);
            out.write(LINE_FEED);
        }

    }

    //TODO: need to optimise this
    private static void processPostScriptBlock(RandomAccessFile in, RandomAccessFile out, long startOfPostScriptBlock) throws IOException {
        in.seek(startOfPostScriptBlock);
        int b;
        while ((b = in.read()) >= 0) {
            out.write(b);
        }
    }

    private static void processMetadata(RandomAccessFile in, RandomAccessFile out, Map<String, String> newOptionsMap) throws IOException {
        Map<String, String> optionsFromFileMap = new HashMap<>();
        String line;
        while ((line = in.readLine()) != null) {
            if (line.startsWith(PJL_SET)) {
                handlePjlSetStatement(out, newOptionsMap, optionsFromFileMap, line);

            } else if (line.startsWith(AT_SIGN_CHAR)) {
                writeLineToFile(line, out);

            } else if (line.indexOf(Character.valueOf('\u001B')) == 0) {
                writeLineToFile(line, out);

            } else if (!line.startsWith(AT_SIGN_CHAR)) {
                for (Map.Entry<String, String> entry : newOptionsMap.entrySet()) {
                    if (optionsFromFileMap.get(entry.getKey()) == null) {
                        optionsFromFileMap.put(entry.getKey(), entry.getValue());
                    }
                }
                for (byte b : line.getBytes()) {
                    out.write(b);
                }
                out.write(LINE_FEED);
                break;

            }
        }
    }

    private static void handlePjlSetStatement(RandomAccessFile out, Map<String, String> newOptionsMap, Map<String, String> optionsFromFileMap, String line) throws IOException {
        int equalsSignPos = line.indexOf('=');
        if (equalsSignPos <= 0) {
            return;
        }

        String key = line.substring(PJL_SET.length(), equalsSignPos).trim().toUpperCase();
        String value = line.substring(equalsSignPos + 1).trim();
        System.out.println("key: " + key);

        String newValue;

        if (newOptionsMap.get(key) != null) {
            newValue = newOptionsMap.get(key);
            System.out.println("value: " + newOptionsMap.get(key));
        } else {
            newValue = value;
            System.out.println("value: " + value);
        }
        StringBuilder newSetStatement = new StringBuilder(PJL_SET.length() + key.length() + newValue.length() + EQUALS_OP.length());
        newSetStatement.append(PJL_SET);
        newSetStatement.append(key);
        newSetStatement.append(EQUALS_OP);
        newSetStatement.append(newValue);
        System.out.println(newSetStatement.toString());
        writeLineToFile(newSetStatement.toString(), out);
        optionsFromFileMap.put(key, value);
    }

    //TODO: need to figure-out why it's not writing correctly
    private static void processHeader(RandomAccessFile in, RandomAccessFile out) throws IOException {

        StringBuilder line = new StringBuilder();
        int i;
        while ((i = in.read()) != 10) {
            char c = (char) i;
            line.append(Character.valueOf(c));
        }
        if (i == 10) {
            line.append("\n");
        }

        //String header = in.readLine();
        writeLineToFile(line.toString(), out);
    }

    private static void testFileIfMatch(RandomAccessFile in1, RandomAccessFile in2) throws IOException {
        int i1 = 0, i2 = 0;
        while (true && (i1 >= 0) ) {
            i1 = in1.read();
            i2 = in2.read();

            if (i1 != i2 ) {
                break;
            }

        }

        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);
        System.out.println("i1 file pos= " + in1.getFilePointer());
        System.out.println("i2 file pos = " + in2.getFilePointer());
    }

    private static void processSample(RandomAccessFile in, RandomAccessFile out) throws IOException {

        int i;
        while ((i = in.read()) >= 0) {
            if (i == AT_SIGN) {
                StringBuilder line = new StringBuilder();
                line.append("@");
                i = in.read();
                if (i == P_CHAR) {
                    line.append("P");
                    while ((i = in.read()) != 10) {
                        char c = (char) i;
                        line.append(Character.valueOf(c));
                    }
                    if (i == LINE_FEED) {
                        line.append("\n");
                    }
                    writeLineToFile(line.toString(), out);
                } else {
                    writeLineToFile(line.toString(), out);
                    out.write(Character.valueOf((char) i));
                }
            } else {
                out.write(Character.valueOf((char) i));
            }
        }
    }

    private void oldCode() {
        try {
            long pos = 0;
            Path path = Paths.get("res/sample3.pjl");
            Path pathOut = Paths.get("res/sample3_out.pjl");

            if (pathOut.toFile().exists()) {
                pathOut.toFile().delete();
            }
//            try (BufferedReader reader=Files.newBufferedReader(path, Charset.forName("UTF-8")) ){
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    if (line.startsWith("@")) {
//                        pos += line.length() + 2;
//                        System.out.println(line);
//
//                    } else {
//                        System.out.println("pos: " + pos);
//                        break;
//                    }
//                }
//                reader.close();
//            }
            //open the file in binary mode
            RandomAccessFile in = new RandomAccessFile(path.toFile().getAbsolutePath(), "r");
            RandomAccessFile out = new RandomAccessFile(pathOut.toFile().getAbsolutePath(), "w");

            String header = in.readLine();
            System.out.println("length: " + header.length());
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("@PJL")) {
                    //System.out.println();
                } else {
                    System.out.println("footer: " + line);
                    pos = in.getFilePointer();
                    //break;
                }
            }
            int remainingBytesToRead = (int) in.length() - (int) pos;
            byte[] b = new byte[remainingBytesToRead];
            in.read(b, 0, b.length);
            String x = Arrays.toString(b);
            System.out.println(x);
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
