import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Examples from the Java 8 book
 * Created by dmorales on 16/11/2015.
 */
public class Test {

    public static void main(String[] args) {

        final List<String> friends =
                Arrays.asList("Darwin", "Nova", "Daniel", "Felicity", "Rita", "Danilo");

        for (String name : friends) {
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

        friends.stream().filter(name -> name.startsWith("D")).forEach(name -> System.out.println("name = " + name));

        final List<String> uppercaseNames = friends.stream().map(String::toUpperCase).collect(Collectors.toList());


        System.out.println("uppercaseNames = " + uppercaseNames);

        final List<String> editors = Arrays.asList("Brian", "John", "Eddie", "Nemo");

        final List<String> brothers = Arrays.asList("Don", "Deo", "Dione", "Darwin");

        final Predicate<String> startsWithD = name -> name.startsWith("D");
        final Consumer<String> printName = name -> System.out.println(name);

        editors.stream().filter(startsWithD).forEach(printName);

        int totalChars =  editors.stream().mapToInt(name -> name.length()).sum();

        final Optional<String> aLongName = friends.stream()
                .reduce((name1, name2) ->
                        name1.length() >= name2.length() ? name2 : name1);

        aLongName.ifPresent(printName);

        //iterating a string
        "hello".chars().forEach(ch -> System.out.println("ch = " + ch));
        "hello".chars().forEach(Test::printCharToUpper);
        "hello".chars().forEach(Test::printChar);
        "hello".chars().mapToObj(ch -> String.valueOf((char) ch).toUpperCase())
                .forEach(printName);
        "hell0".chars().filter( ch -> !Character.isDigit(ch)).forEach( ch -> printChar(ch));
        "hell0".chars().filter( Character::isDigit).forEach( ch -> printChar(ch));
        "hell0".chars().filter( Character::isAlphabetic).forEach( ch -> printChar(ch));
        "hell0".chars().filter( Character::isDigit).forEach( ch -> printChar(ch));

//        final BiFunction<StringBuilder, String, StringBuilder> accumulator =
//                (builder, name) -> {
//                    //if (builder.length() > 0) builder.append(",");
//                    builder.append("|| Mister:").append(name);
//                    return builder;
//                };
//
//        final Stream<String> stringStream = Stream.of("John Lennon", "Paul Mccartney"
//                , "George Harrison", "Ringo Starr");
//        final StringBuilder reduce = stringStream
//                .filter(a -> a != null)
//                .reduce(new StringBuilder(), accumulator, (left, right) -> {
//                    System.out.println("left = " + left);
//                    return left.append(right);
//                });
//        System.out.println(reduce);
//        System.out.println(reduce.length());

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
    final Function<String, Predicate<String>> startsWithLetterV2 = letter -> name -> name.startsWith(letter);

    public void pickName(final List<String> names, final String startingLetter) {
        final Optional<String> foundName = names.stream().filter(startsWithLetterV2.apply(startingLetter)).findFirst();
        //foundName.filter().orElseGet(() -> "My Name");
        System.out.println(foundName.orElse("No name found"));
    }

    private static void printChar(int aChar) {
        System.out.println((char)(aChar));
    }

    private static void printCharToUpper(int aChar) {
        System.out.println( String.valueOf((char)(aChar)).toUpperCase() );
    }


}
