package java_data_structures;

/**
 * Created by darwinmorales on 7/06/2016.
 */
public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E first();
    E dequeue();

}
