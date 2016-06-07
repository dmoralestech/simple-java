package java_data_structures;

/**
 * Created by darwinmorales on 7/06/2016.
 */
public class ArrayQueue<E> implements Queue<E>{

    private E[] data;
    private int indexFront = 0;
    private int size = 0;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E dequeue() {
        return null;
    }
}

