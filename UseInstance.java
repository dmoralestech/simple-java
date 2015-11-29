/**
 * Created by darwinmorales on 29/11/2015.
 */
@FunctionalInterface
public interface UseInstance<T, X extends Throwable> {
    void accept(T instance) throws X;
}
