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

}
