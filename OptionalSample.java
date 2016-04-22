import java.util.Optional;

/**
 * Created by darwinmorales on 21/04/2016.
 */
public class OptionalSample {

    private static class Person {
        private Car car;

        public Car getCar() {
            return car;
        }
    }

    private static class Car {
        private Insurance insurance;

        public Insurance getInsurance() {
            return insurance;
        }
    }

    private static class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Person person2 = null;
        Optional<Person> person = Optional.ofNullable(person2);
        person.ifPresent( p -> {
            Optional<Car> car =  Optional.ofNullable(p.getCar());
            car.ifPresent( c -> {
                Optional<Insurance> insurance = Optional.ofNullable(c.getInsurance());
                insurance.ifPresent(i -> {
                    i.getName();
                });
            });
        });

//        person.orElse()
//
//        Optional<Car> car = Optional.ofNullable(person.get().getCar());
//
//                .orElse(new Person())
//
//                .map( p -> Optional.of(p))
//                .flatMap( p -> p.getCar())
//                .flatMap(c -> c.getInsurance())
//                .flatMap (i -> i.getName())
//                .orElse("none");
//
//        System.out.println("name = " + name);
    }

}

