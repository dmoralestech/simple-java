import java.awt.*;
import java.awt.event.ContainerAdapter;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;


/**
 * Created by dmorales on 19/11/2015.
 */
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(final Person other) {
        return age - other.age;
    }

    public static void main(String[] args) {

    }
    public  void main1() {
        final List<Person> people = Arrays.asList(
                new Person("A", 30),
                new Person("B", 21),
                new Person("C", 72),
                new Person("D", 23),
                new Person("E", 24),
                new Person("F", 21),
                new Person("A", 21)

        );



        List<Person> ascendingAge = people.stream()
                //.sorted((person1, person2) -> person1.ageDifference(person2))
                .sorted( Person::ageDifference)
                .collect( Collectors.toList());

        printPeople("asceding by age", ascendingAge);

        Comparator<Person> compareAscending = (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();

        Comparator<Person> compareNameAscending = (person1, person2) -> person1.getName().compareTo(person2.getName());

        List<Person> descendingAge = people.stream()
                .sorted(compareDescending)
                .collect(Collectors.toList());

        people.stream().min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("youngest = " + youngest));

        people.stream().max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("eldest = " + eldest));

        people.sort(java.util.Comparator.comparing(Person::getAge));

        Function<Person, String> methodRec = Person::getName;
        Function<Person, String> methodRec2 = (Person p) -> p.getName();

        Function<String, Integer> stringToInteger = Integer::parseInt;
        BiPredicate<List<String>, String> contains = List::contains;

        Function<Double, Double>  as = Math::abs;
        Double d =  as.apply(3.4);
        // Math.abs(3.4);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(2);    // returns 6

        List<String> str = Arrays.asList("a", "b", "A", "B");
        Comparator<String>  comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };

        str.sort(comparator);
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);

        final Function<Person, String> byName = person -> person.getName();
        final Function<Person, Integer> byAge = person -> person.getAge();

        printPeople("", people.stream().sorted(comparing(byName)).collect(Collectors.toList()));
        printPeople("", people.stream().sorted(comparing(byName).thenComparing(byAge)).collect(Collectors.toList()));

        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect( Collectors.groupingBy( Person::getAge));

        Function<Person, String> classifierName = Person::getName;

        Map<String, List<Person>> peopleByName = people.stream()
                .collect( Collectors.groupingBy( classifierName));

        long numOfRecs = people.stream()
                .collect(Collectors.counting());

        Function<Person, Integer> classifierAge = Person::getAge;

        Map<Integer, List<Person>> ageByName3 = people.stream()
                .collect( Collectors.groupingBy( classifierAge));


        Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
                .collect(groupingBy( Person::getAge, mapping(Person::getName, toList())));


        List<Person> olderThan20 = people.stream()
                                            .filter(person -> person.getAge() > 20)
                                            .collect(Collectors.toList());

        Comparator<Person> byAge2 = (person1, person2) -> person1.getAge() - person2.getAge();
        //Comparator<Person> byAge2 = Comparator.comparing(Person::getAge);
        BinaryOperator<Person> binaryOperator = (new BinaryOperator<Person>() {
            @Override
            public Person apply(Person person, Person person2) {
                return person;
            }
        });

        BinaryOperator<Person> binaryOperator1 = (person1, person2) -> person1;

//        Map<String, Optional<Person>> oldestPersonOfEachLetter =
//                people.stream()
//                        .collect( groupingBy( person -> person.getName().substring(0, 1),
//                                            reducing( BinaryOperator.maxBy( byAge2))
//                                 )
//                        );
                                //reducing( BinaryOperator.maxBy( byAge2))));


        Predicate<String> predicateSample = pred -> false;

        Predicate<String> predicateSample2 = ( new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        });

        Function<Integer, String> functionAnonymous = ( new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "some string";
            }
        });

        Function<Integer, String> functionAnonymous2 = intElem -> "some string";

        Consumer<String> consumerSample = (new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        });

        Supplier<String> supplierSample = (new Supplier<String>() {
            @Override
            public String get() {
                return "returns some string..";
            }
        });

        BiConsumer<String, Integer> biConsumerSample = (new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {

            }
        });

        TriConsumer<String, Integer, Double> triConsumer = (new TriConsumer<String, Integer, Double>() {
            @Override
            public void accept(String s, Integer integer, Double aDouble) {

            }
        });

        BinaryOperator<String> binaryOperatorSample = (new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                return null;
            }
        });

        BiPredicate<String, Integer> biPredicateSample = (new BiPredicate<String, Integer>() {
            @Override
            public boolean test(String s, Integer integer) {
                return false;
            }
        });

        Supplier<String> supplierSample2 = () -> "returns some string...";




        UnaryOperator<Integer> unaryOperator = (new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return 23;
            }
        });

        UseInstance<String, IOException> useInstance = (new UseInstance<String, IOException>() {
            @Override
            public void accept(String instance) throws IOException {

            }
        });

        Consumer<String> consumerSample2 = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };

        people.stream().map(person -> person.getName()).distinct().forEach(System.out::println);
        
        // need to play more with the Collectors utility class because I don't fully understand it yet.

        Function<String, Integer> strToInt = Integer::parseInt;

        Function<String, Integer> strToInt2 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        strToInt.apply("3");
        strToInt2.apply("3");


    }

    private static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

    public void greetName() {
        NameGreeter<String> consumerExample = new NameGreeter<>();
        consumerExample.accept("Darwin");
    }

    class NameGreeter<String> implements Consumer<String>{
        @Override
        public void accept(String s) {
            System.out.println("Hello " + s);
        }
    }

    static Predicate<String> containsChar(String source, String s1) {
        return  s -> source.indexOf(s1) > 0 ;
    }

    static Predicate<String> isStringLengthEqualTo(String source, int lengthToTest) {
        return s -> source.length() == lengthToTest;
    }

    private String getSomething(String searchString) {

        return Optional.of(searchString)
                .filter(s -> s.length() == 7)
                .flatMap( s -> Optional.of(s))
                .filter( s -> s.indexOf(" ") > 0)
                .get();

    }


}
