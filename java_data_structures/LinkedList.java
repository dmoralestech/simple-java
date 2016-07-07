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
    private Node n;

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

    public Node get(int i) {

    }

    @Override
    public Iterator<E> iterator() {
        n = root;
        Iterator<E> iter = new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public E next() {
                E res = (E) n.getData();
                n = n.getNext();
                return res;
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
