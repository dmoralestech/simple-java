import java.util.function.Function;

/**
 * Created by dmorales on 11/12/2015.
 */
public class Letter {

    public static String addHeader(String text) {
        return "From Darwin:\n" + text;
    }

    public static String addFooter(String text) {
        return text + "\nKind Regards,\n  Darwin";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }

    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        Function<String, String> transformationPipeline2 = addHeader
                .andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("Body labda Text "));

    }
}
