package java_data_structures;

/**
 * Created by darwinmorales on 10/06/2016.
 */
public class LinkedQueue<E> implements Queue<E> {
    SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public LinkedQueue() {
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

}
