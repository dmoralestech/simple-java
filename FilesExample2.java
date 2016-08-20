import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by darwinmorales on 20/08/2016.
 */
public class FilesExample2 {

    public static void main(String[] args) {

        try {
            Path path = Paths.get("js/template.js");
            try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }

}
