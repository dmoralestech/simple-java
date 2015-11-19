import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        final List<Person> people = Arrays.asList(
            new Person ("A", 30),
            new Person ("B", 21),
            new Person ("C", 72),
            new Person ("D", 23),
            new Person ("E", 24)
        );

        List<Person> ascendingAge = people.stream()
                .sorted((person1, person2) -> person1.ageDifference(person2))
                .collect(Collectors.toList());

        printPeople("asceding by age", ascendingAge);

    }

    private static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

}
