package Optional;

import java.util.*;

public class OptionalMain {

    public static void main(String[] args) {
        OptionalMain optionalMain = new OptionalMain();
        String insuranceName = optionalMain.getCarInsuranceName(null);
    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }
}
