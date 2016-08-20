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

            if (pathOut.toFile().exists()) {
                pathOut.toFile().delete();
            }

            // I could check for duplicates

            Map<String, String> newOptionsMap = new HashMap<>();
            newOptionsMap.put("USERID", "jordan");
            newOptionsMap.put("RENDERMODE", "BLACKWHITE GREYSCALE");
            newOptionsMap.put("HOSTPORTNAME", "\"0.19.20.0\"");

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
                        StringBuilder newSetStatement = new StringBuilder(PJL_SET.length() + key.length() + newValue.length() + 3);
                        newSetStatement.append(PJL_SET);
                        newSetStatement.append(key);
                        newSetStatement.append(" = ");
                        newSetStatement.append(newValue);
                        System.out.println(newSetStatement.toString());
                    }

                } else if (!line.startsWith("@")) {
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
