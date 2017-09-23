import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExample {
    public static void main(String[] args) {
        Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);

        Predicate<String> pred =  startsWithLetter.apply("a");
        boolean res =  pred.test("abc");
        System.out.println(res); //true
        // OR SIMPLY
        System.out.println(startsWithLetter.apply("a").test("abc")); //true

        Function<String, Integer> stringLength = input -> input.length();
        System.out.println(stringLength.apply("Test"));  // 4
    }
}
