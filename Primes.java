import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by darwinmorales on 6/12/2015.
 */
public class Primes {

    public static boolean isPrime(final int number) {
        System.out.println("******** isPrime: " + number  );
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(  divisor -> {
            System.out.printf("%d  %d == 0  %d \n", number, divisor, number % divisor  );  return number % divisor == 0;} );
    }

    private static int primeAfter(final int number) {

        if (isPrime(number + 1))
            return number + 1;
        else
            return primeAfter(number + 1);

    }

    public static List<Integer> primes (final int number, final int count) {
        return Stream.iterate( primeAfter(number - 1), p -> {
            System.out.println("prime after: " + p); return primeAfter(p);})
                .limit(count)
                .collect(Collectors.<Integer>toList());
    }

    public static void main(String[] args) {

        Stream.iterate(1, p -> p * 2 ).limit(10).forEach(p -> System.out.println("p= " + p));

        System.out.println("10 primes  from 1: " + primes(1, 10));

        System.out.println("5 primes  from 100: " + primes(100, 5));
    }
}
