/**
 * Created by dmorales on 30/11/2015.
 */
@FunctionalInterface
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);

}
