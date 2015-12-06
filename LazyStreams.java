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
        List<String> names = Arrays.asList("Brad", "Mel", "Kate", "Kim", "Jack", "Joe");

            // instead of doing all the filters all at once, it goes through the element one by one and pass it to the next function in the chain...
            // It will only process the things that it needs to satisfy the requirements. In this case it would only process until "Mel"
        final String firstNamesWith3Letters = names.stream()
                .filter(name -> length(name)==3)
                .map(name -> toUpper(name))
                .findFirst()
                .get();

        System.out.println("firstNamesWith3Letters = " + firstNamesWith3Letters);

    }

}
