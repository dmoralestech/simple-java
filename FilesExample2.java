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

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class FilesExample2 {

    public static void main(String[] args) {
        try {
            long pos = 0;
            Path path = Paths.get("res/sample3.pjl");
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
            String header = in.readLine();
            System.out.println("length: " + header.length());
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("@PJL")) {
                    //System.out.println();
                } else {
                    System.out.println("footer: " + line);
                    pos = in.getFilePointer();
                    break;
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
