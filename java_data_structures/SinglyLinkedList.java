package java_data_structures;

/**
 * Created by darwinmorales on 10/06/2016.
 */
public class SinglyLinkedList<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
        public E getElement() {
            return element;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> n){
            next = n;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first() {

    }

    public E last() {

    }

    public void addFirst(E e) {

    }

    public void addLast(E e) {

    }

    public E removeFirst() {

    }

    public E removeLast() {

    }


}
