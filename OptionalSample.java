import java.util.Optional;

/**
 * Created by darwinmorales on 21/04/2016.
 */
public class OptionalSample {

    private static class Person {
        private Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }
    }

    private static class Car {
        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    private static class Insurance {
        private Optional<String> name;

        public Optional<String> getName() {
            return name;
        }
    }

    public static void main(String[] args) {
//        String name = Optional.ofNullable(new Person())
//                .orElse(new Person())

//                .map( p -> Optional.of(p))
//                .flatMap( p -> p.getCar())
//                .flatMap(c -> c.getInsurance())
//                .flatMap (i -> i.getName())
//                .orElse("none");

//        System.out.println("name = " + name);
    }
}
