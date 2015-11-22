import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

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

            Files.newDirectoryStream( Paths.get("."), path -> path.toString().endsWith("java"))
                    .forEach(System.out::println);

            final File[] hiddenFiles = new File(".").listFiles(file -> file.isHidden());
            final File[] hiddenFiles2 = new File(".").listFiles(File::isHidden);

            List<File> files = Stream.of(new File(".").listFiles())
                    .flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
                    .collect(toList());



        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
