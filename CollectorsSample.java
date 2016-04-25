import java.util.*;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.summarizingDouble;

/**
 * Created by darwinmorales on 24/04/2016.
 */
public class CollectorsSample {
    private static class Customer {
        public String first;
        public String last;

        public Customer(String first, String last) {
            this.first = first;
            this.last = last;
        }

        public String toString() {
            return "Customer(" + first + " " + last + ")";
        }
    }

    private static class Person {
        String name;
        int group;
        int age;

        // Contructor, getter and setter

        private Person(String name, int group, int age) {
            this.name = name;
            this.group = group;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGroup() {
            return group;
        }

        public void setGroup(int group) {
            this.group = group;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    private static class  Data {
        long average;
        long sum;

        public Data(long average, long sum) {
            this.average = average;
            this.sum = sum;
        }

        // Getter and setter


        public long getAverage() {
            return average;
        }

        public void setAverage(long average) {
            this.average = average;
        }

        public long getSum() {
            return sum;
        }

        public void setSum(long sum) {
            this.sum = sum;
        }
    }

    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(new Customer("Darwin", "Morales"), new Customer("Nova", "Morales"));

//        Map<String, Customer> res2 = customers.stream()
//                .flatMap(
//                        c -> Stream.of(c.first, c.last)
//                                .map(k -> new AbstractMap.SimpleImmutableEntry<>(k, c))
//                ).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));


        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Person One", 1, 18));
        persons.add(new Person("Person Two", 1, 20));
        persons.add(new Person("Person Three", 1, 30));
        persons.add(new Person("Person Four", 2, 30));
        persons.add(new Person("Person Five", 2, 29));
        persons.add(new Person("Person Six", 3, 18));


        Map<Integer, Data> result = persons.stream().collect(
                groupingBy(Person::getGroup,
                        collectingAndThen(summarizingDouble(Person::getAge),
                                dss -> new Data((long)dss.getAverage(), (long)dss.getSum()))));
    }
}
