import java.util.Arrays;
import java.util.List;

/**
 * Created by darwinmorales on 5/12/2015.
 */
public class LazyStreams {
    private static int length(final String name) {
        System.out.println("getting length for: " + name);
        return name.length();
    }

    private static String toUpper(final String name) {
        System.out.println("converting to uppercase: " + name);
        return name.toUpperCase();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Brad", "Kate", "Kim", "Jack", "Joe");

        final String firstNamesWith3Letters = names.stream()
                .filter(name -> length(name)==3)
                .map(name -> toUpper(name))
                .findFirst()
                .get();

        System.out.println("firstNamesWith3Letters = " + firstNamesWith3Letters);

    }

}
