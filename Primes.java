import java.util.stream.IntStream;

/**
 * Created by darwinmorales on 6/12/2015.
 */
public class Primes {

    public static boolean isPrime(final int number) {
        return number > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(number)).noneMatch(divisor -> number % divisor == 0);
    }

    private static int primeAfter(final int number) {

        if (isPrime(number + 1))
            return number + 1;
        else
            return primeAfter(number + 1);

    }
}
