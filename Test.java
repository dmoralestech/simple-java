import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Examples from the Java 8 book
 * Created by dmorales on 16/11/2015.
 */
public class Test {

    public static void main(String[] args) {

        final List<String> friends =
                Arrays.asList("Darwin", "Nova", "Daniel", "Felicity", "Rita", "Danilo");

        for(String name : friends) {
            System.out.println(name);
        }

        friends.forEach(new Consumer<String>() {
            public void accept(final String name) {
                System.out.println(name);
            }
        });

        friends.forEach((final String name) -> System.out.println(name));


        friends.forEach(name -> System.out.println(name));


    }

}
