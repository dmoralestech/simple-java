package java_data_structures;

/**
 * Created by darwinmorales on 11/06/2016.
 */
public interface Stack<E> {
    int size();

    boolean isEmpty();

    void push(E e);

    E top();

    E pop();

}
