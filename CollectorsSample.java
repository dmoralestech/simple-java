import java.util.*;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toMap;

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

    private static class Customer2 {
        public String first;
        public String last;

        public Customer2( String first, String last) {
            this.first = first;
            this.last = last;
        }

        public String toString() {
            return "Customer {" + first + " " + last + "}";
        }
    }

    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(new Customer("Darwin", "Morales"), new Customer("Nova", "Morales"));

//        Map<String, Customer> res2 = customers.stream()
//                .flatMap(
//                        c -> Stream.of(c.first, c.last)
//                                .map(k -> new AbstractMap.SimpleImmutableEntry<>(k, c))
//                ).collect(toMap(Map.Entry::getKey, Map.Entry::getValue));


    }
}
