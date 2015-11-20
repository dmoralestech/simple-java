import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
                new Person("A", 30),
                new Person("B", 21),
                new Person("C", 72),
                new Person("D", 23),
                new Person("E", 24)
        );

        List<Person> ascendingAge = people.stream()
                //.sorted((person1, person2) -> person1.ageDifference(person2))
                .sorted(Person::ageDifference)
                .collect(Collectors.toList());

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

        final Function<Person, String> byName = person -> person.getName();
        final Function<Person, Integer> byAge = person -> person.getAge();

        printPeople("", people.stream().sorted(java.util.Comparator.comparing(byName)).collect(Collectors.toList()));
        printPeople("", people.stream().sorted(java.util.Comparator.comparing(byName).thenComparing(byAge) ).collect(Collectors.toList()));

        Map<Integer, List<Person>> peopleByAge = people.stream().collect(Collectors.groupingBy( Person::getAge));

        Map<Integer, List<String>> nameOfPeopleByAge = people.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));

        List<Person> olderThan20 = people.stream().filter(person -> person.getAge() > 20).collect(Collectors.toList());




    }

    private static void printPeople(final String message, final List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

}
