package java_data_structures;

/**
 * Created by darwinmorales on 7/06/2016.
 */
public class ArrayQueue<E> implements Queue<E>{

    private E[] data;
    private int indexFront = 0;
    private int size = 0;

    public ArrayQueue() {
    }

    public ArrayQueue(int size) {
        data = (E[]) new Object[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {

    }

    @Override
    public E first() {
        if (isEmpty())
            return null;

        return data[indexFront];
    }

    @Override
    public E dequeue() {
        return null;
    }
}

