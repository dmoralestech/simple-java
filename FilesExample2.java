import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class FilesExample2 {

    public static void main(String[] args) {

        String sourceFullPath;
        String destFullPath;
        String addNewOptions;
        Map<String, String> optionsArgs = new HashMap<>();

        for (String arg: args) {
            String[] keys = arg.split("=");
            String key = keys[0];
            String value = keys[1];
            if (key.startsWith("-")) {
                key = key.substring(1);
            }
            optionsArgs.put(key.toUpperCase(), value);
        }

        sourceFullPath = optionsArgs.get("SOURCE");
        destFullPath = optionsArgs.get("DESTINATION");
        addNewOptions = optionsArgs.get("ADDNEW");

        if (sourceFullPath == null || destFullPath == null) {
            // error
        }

//
//        CommandLineParser parser = new DefaultParser();
//
//        Options options = new Options();
//        options.addOption("s", true, "source");
//        options.addOption("d", true, "source");
//        options.addOption("a", true, "addNew");
//
//        try {
//            CommandLine commandLine = parser.parse(options , args);
//            System.out.println(commandLine.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        // -source="sss" -destination="ddd" -addNew=true -key1="v1" -key2="v2"
        readFileV2();
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

    public static void readFileV2() {
        try {
            Path path = Paths.get("res/sample3.pjl");
            Path pathOut = Paths.get("res/sample3_out.pjl");

            final String PJL_SET = "@PJL SET ";
            final String AT_SIGN = "@";
            final String EQUALS_OP = " = ";


            if (pathOut.toFile().exists()) {
                pathOut.toFile().delete();
            }

            // I could check for duplicates
            // I could save all the options to a separate map


            Map<String, String> newOptionsMap = new HashMap<>();
            newOptionsMap.put("USERID", "jordan");
            newOptionsMap.put("RENDERMODE", "BLACKWHITE GREYSCALE");
            newOptionsMap.put("HOSTPORTNAME", "\"0.19.20.0\"");
            newOptionsMap.put("NEW_OPTION", "COLOR");
            newOptionsMap.put("NEW_OPTION2", "GREY");

            Map<String, String> optionsFromFileMap = new HashMap<>();

            //open the file in random access mode
            RandomAccessFile in = new RandomAccessFile(path.toFile().getAbsolutePath(), "r");
            RandomAccessFile out = new RandomAccessFile(pathOut.toFile().getAbsolutePath(), "rw");

            String header = in.readLine();
            out.writeChars(header);
            for (byte b: header.getBytes()) {
                out.write(b);
            }
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith(PJL_SET)) {
                    int equalsSignPos = line.indexOf('=');
                    if (equalsSignPos > 0) {
                        String key = line.substring(8, equalsSignPos).trim();
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
                        optionsFromFileMap.put(key, value);
                    }

                } else if (!line.startsWith(AT_SIGN)) {

                    for(Map.Entry<String, String> entry: newOptionsMap.entrySet()) {
                        if (optionsFromFileMap.get(entry.getKey()) == null) {
                            optionsFromFileMap.put(entry.getKey(), entry.getValue());
                        }
                    }

                    break;
                }
            }
            out.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
