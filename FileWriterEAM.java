import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by dmorales on 30/11/2015.
 */
public class FileWriterEAM {

    private final FileWriter writer;

    private  FileWriterEAM(final String fileName) throws IOException {
        this.writer = new FileWriter(fileName);
    }

    private void close() throws IOException {
        System.out.println("close call automatically...");
        writer.close();
    }

    public void writeToFile(final String message) throws IOException {
        writer.write(message);
    }

    public static void use(final String fileName, final UseInstance<FileWriterEAM, IOException> block) throws IOException {
        final FileWriterEAM writerEAM = new FileWriterEAM(fileName);

        try {
            block.accept(writerEAM);
        } finally {
            writerEAM.close();
        }
    }

    public static void main(final String[] args) {
        try {
            System.out.println("//" + "START:EAM_USE_OUTPUT");
            FileWriterEAM.use("eam.txt", writerEAM -> writerEAM.writeToFile("sweet"));
            System.out.println("//" + "END:EAM_USE_OUTPUT");

            FileWriterEAM.use("eam2txt", writerEAM -> {
                        writerEAM.writeToFile("how");
                        writerEAM.writeToFile("sweet");
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
