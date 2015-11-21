import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by darwinmorales on 21/11/2015.
 */
public class FilesExamples {

    public static void main(String[] args) {
        try {
            Files.list(Paths.get(".")).forEach(System.out::println);

            Files.list(Paths.get("."))
                    .filter( file -> Files.isDirectory(file))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
