package java_data_structures;

import java.lang.reflect.Array;

/**
 * Created by darwinmorales on 7/06/2016.
 */
public class ArrayQueue<E> implements Queue<E> {

    private E[] data;
    private int indexFront = 0;
    private int size = 0;
    private static final int CAPACITY = 1000;

    public ArrayQueue() {
        this(CAPACITY);
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
        if (size == data.length) {
            throw new Error("Queue is full.");
        }
        int avail = (indexFront + size) % data.length;
        data[avail] = e;
        size++;
    }

    @Override
    public E first() {
        if (isEmpty())
            return null;

        return data[indexFront];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E result = data[indexFront];
        data[indexFront] = null;
        indexFront = (indexFront + 1) % data.length;
        size--;
        return result;

    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(10);

        queue.enqueue(2);
        queue.enqueue(22);
        queue.enqueue(23);
        queue.enqueue(24);
        queue.enqueue(25);
        queue.enqueue(27);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(82);
        queue.enqueue(92);
        queue.enqueue(02);
        queue.enqueue(02);
        queue.enqueue(06);
        queue.enqueue(07);
        queue.enqueue(011);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(12);
        queue.enqueue(13);



    }
}

