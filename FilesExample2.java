import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class FilesExample2 {

    public static void main(String[] args) {
        try {
            int pos = 0;
            Path path = Paths.get("res/test.tmp");
            try (BufferedReader reader=Files.newBufferedReader(path)){
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("@")) {
                        pos += line.length() + 2;
                        System.out.println(line);

                    } else {
                        System.out.println("pos: " + pos);
                        break;
                    }
                }
                reader.close();
            }
            //open the file in binary mode
            RandomAccessFile in = new RandomAccessFile(path.toFile().getAbsolutePath(), "r");
            in.seek(pos);
            String binaryDataAsString = in.readLine();
            System.out.println("length: " + binaryDataAsString.length());
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
