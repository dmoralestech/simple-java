import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dmorales on 8/12/2015.
 */
public class AbstractFactoryJava8 {

    public interface Vehicle {}

    public static class Car implements Vehicle {
        @Override
        public String toString() {
            return "Car";
        }
    }

    public static class Moto implements Vehicle {
        @Override
        public String toString() {
            return "Moto";
        }
    }


    public static class VehicleFactory {
        private final HashMap<String, Supplier<? extends Vehicle>> map = new HashMap<>();

        public void register(String name, Supplier<? extends Vehicle> supplier) {
            map.put(name, supplier);
        }

        public Vehicle create(String name) {
            return map.getOrDefault(name,
                    () -> { throw new IllegalArgumentException("Unkown " + name);})
                    .get();
        }

    }


    public static void main(String[] args) {
        VehicleFactory factory = new VehicleFactory();

        factory.register("car", Car::new);

        Moto moto = new Moto();

        factory.register("moto", () -> moto);

        Vehicle vehicle1 = factory.create("car");
        System.out.println(vehicle1);

        Vehicle vehicle2 = factory.create("moto");
        System.out.println(vehicle2);

        Vehicle vehicle3 = factory.create("doesntexist");
        System.out.println(vehicle3);


    }
}
