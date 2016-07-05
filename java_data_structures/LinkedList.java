package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 3/07/2016.
 */
public class LinkedList<E> implements Iterable<E> {

    public class Node<E>  {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    private Node root;
    private int size = 0;

    public void add(E e) {
        Node n = new Node(e);
        n.setNext(null);
        if (size == 0) {
            root = n;
        } else {
            Node temp = root;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(n);
        }

        size++;

    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iter = new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                return null;
            }
        };
        return  iter;
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for (String value: list) {
            System.out.println("value = " + value);
        }


    }

}
