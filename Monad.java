import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by dmorales on 20/04/2016.
 */
public class Monad<T> {
    private T value;

    private Monad(T value) {
        this.value = value;
    }

    public static <T> Monad<T> unit(T value) {
        return new Monad<T>(value);
    }

    public <R> Monad<R> flatMap(Function<T, Monad<R>> func) {
        return func.apply(this.value);
    }

    public T get() {
        return value;
    }

    public static void main(String[] args) {
        Monad<Integer> intMonad = Monad.unit(2);
        Monad<Double> dblMonad = intMonad.flatMap( (i) -> Monad.unit(Math.sqrt(i)));

        System.out.println("dblMonad = " + dblMonad);

        Double dblOptional = Optional.of(2)
                                            .flatMap( i -> Optional.of(Math.sqrt(i)))
                                            .flatMap( d -> Optional.of(d * d))
                                            .get();

        double result = intMonad.flatMap( (i) -> Monad.unit(Math.sqrt(i)))
                                .flatMap( (d) -> Monad.unit(d * d))
                                .get();

        double result2 = intMonad.flatMap( (d) -> Monad.unit(d * d))
                                 .flatMap( (i) -> Monad.unit(Math.sqrt(i)))
                                 .get();

        System.out.println("result = " + result);
        System.out.println("result2 = " + result2);
        System.out.println(intMonad.get().equals(intMonad.flatMap(i -> Monad.unit(i)).get()));
    }

}
