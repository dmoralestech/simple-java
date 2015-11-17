import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        friends.forEach(name -> System.out.println(name.toUpperCase()));

        friends.stream().map(name -> name.length()).forEach(count -> System.out.print(count + " "));

        friends.stream().filter(name -> name.startsWith("D")).forEach( name -> System.out.println("name = " + name));

        final List<String> uppercaseNames =  friends.stream().map(String::toUpperCase).collect(Collectors.toList());


        System.out.println("uppercaseNames = " + uppercaseNames);


        final List<String> editors = Arrays.asList("Brian", "John", "Eddie", "Nemo");

        final List<String> brothers = Arrays.asList("Don", "Deo", "Dione", "Darwin");

        final Predicate<String> startsWithD = name -> name.startsWith("D");
        final Consumer<String> printName = name -> System.out.println( name);

        editors.stream().filter(startsWithD).forEach(printName);


    }

    //smelly version since it's static
    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    // we can use this inside a function when we need it
    final Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
        Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
        return checkStarts;
    };

    //a concise version
    final Function<String, Predicate<String>> startsWithLetterV2 =  letter ->  name -> name.startsWith(letter);

    public void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream().filter(startsWithLetterV2.apply(startingLetter)).findFirst();

        System.out.println(foundName.orElse("No name found"));
    }



}
