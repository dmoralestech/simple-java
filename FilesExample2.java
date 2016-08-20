import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class FilesExample2 {

    //TODO: logging
    //TODO: test
    //TODO: comments


    final static String PJL_SET = "@PJL SET ";
    final static String AT_SIGN = "@";
    final static String EQUALS_OP = " = ";

    public static void main(String[] args) throws Exception {

        // -source="sss" -destination="ddd" -addNew=true -key1="v1" -key2="v2"

//        ValidateCommandLine commandLine = new ValidateCommandLine(args);
//        commandLine.parse();

        Map<String, String> newOptionsMap = new HashMap<>();
        newOptionsMap.put("USERID", "jordan");
        newOptionsMap.put("RENDERMODE", "BLACKWHITE GREYSCALE");
        newOptionsMap.put("HOSTPORTNAME", "\"0.19.20.0\"");
        newOptionsMap.put("NEW_OPTION", "COLOR");
        newOptionsMap.put("NEW_OPTION2", "GREY");

        processPJLFile("res/sample3.pjl", "res/sample3_out.pjl", newOptionsMap);

    }

    public static boolean processPJLFile(String sourceFile, String destFile, Map<String, String> newOptionsMap) throws Exception {
        RandomAccessFile in = null;
        RandomAccessFile out = null;
        try {
            Path path = Paths.get(sourceFile);
            Path pathOut = Paths.get(destFile);

            if (pathOut.toFile().exists()) {
                pathOut.toFile().delete();
            }

            // I could check for duplicates
            // I could save all the options to a separate map
            in = new RandomAccessFile(path.toFile().getAbsolutePath(), "r");
            out = new RandomAccessFile(pathOut.toFile().getAbsolutePath(), "rw");

            processHeader(in, out);
            processMetadata(in, out, newOptionsMap);
            processPostScriptBlock(in, out, in.getFilePointer());

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

        return true;

    }

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

            } else if (!line.startsWith(AT_SIGN)) {
                for (Map.Entry<String, String> entry : newOptionsMap.entrySet()) {
                    if (optionsFromFileMap.get(entry.getKey()) == null) {
                        optionsFromFileMap.put(entry.getKey(), entry.getValue());
                    }
                }
                break;

            }
        }
    }

    private static void handlePjlSetStatement(RandomAccessFile out, Map<String, String> newOptionsMap, Map<String, String> optionsFromFileMap, String line) throws IOException {
        int equalsSignPos = line.indexOf('=');
        if (equalsSignPos <= 0) {
            return;
        }

        String key = line.substring(PJL_SET.length(), equalsSignPos).trim();
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
        out.writeBytes(newSetStatement.toString());
        optionsFromFileMap.put(key, value);
    }

    private static void processHeader(RandomAccessFile in, RandomAccessFile out) throws IOException {
        String header = in.readLine();
        out.writeChars(header);
        for (byte b : header.getBytes()) {
            out.write(b);
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
