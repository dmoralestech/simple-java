package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 3/07/2016.
 */
public class LinkedList<E> implements Iterable<E> {

    public static class Node<E>  {
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


    private Node<E> root;
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

    public E get(int i) {
        Node temp = root;
        for(int j = 0; j <= i - 2; j ++ ) {
            temp = temp.getNext();
        }
        return (E) temp.getData();

    }

    private Node removeFirst() {
        if (root == null) {
            return null;
        } else {
            root = root.getNext();
            return root;
        }
    }

    public void deleteNode(E data) {
        Node prev = null;
        Node current = root;

        //first find the node we need to delete
        while(current != null) {
            if (current.getData().equals(data)) {
                break;
            }
            prev = current;
            current = current.getNext();
        }

        if (current == root ) {
            root = root.getNext();
        } else if (current != null) {
            prev.setNext(current.getNext());
        }

    }

    public Node reverse(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node toDo = head.getNext();
        Node reverse = head;

        reverse.setNext(null);

        while (toDo != null) {
            Node temp = toDo;
            toDo = toDo.getNext();

            temp.setNext(reverse);
            reverse = temp;
        }

        return reverse;
    }

    public boolean compare(Node list1, Node list2) {

        if (list1 == null && list2 == null) {
            return true;
        }

        while (list1.getData().equals(list2.getData()) && list1 != null && list2 != null) {
            list1 = list1.getNext();
            list2 = list2.getNext();
        }

        if (list1 == null && list2 == null ) {
            return true;
        }

        return false;

    }

    public Node<Integer> addTwoNumbers(Node<Integer> list1, Node<Integer> list2) {
        int carry = 0;

        Node<Integer> newHead = new Node(0);
        Node<Integer> p1 = list1;
        Node<Integer> p2 = list2;
        Node<Integer> p3 = newHead;

        while (p1 != null || p2 != null) {

            if (p1 != null) {
                carry += (Integer) p1.getData();
                p1 = p1.getNext();
            }

            if (p2 != null) {
                carry += (Integer) p2.getData();
                p2 = p2.getNext();
            }

            p3.setNext(new Node(carry % 10));
            p3 = p3.getNext();
            carry /= 10;

        }

        if (carry == 1){
            p3.setNext(new Node(1));
        }

        return newHead.getNext();
    }

    public Node<E> getRoot() {
        return root;
    }

    public void print(Node node) {
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    public boolean deleteMiddleNode(Node<E> n) {
        if (n == null || n.getNext() == null) {
            return false;
        }

        Node<E> next = n.getNext();
        n.setData(next.getData());
        n.setNext(next.getNext());
        return true;
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

        int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);
        System.out.println(Integer.toBinaryString(val & bitmask));


        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        Node<Integer> list3 = list2.addTwoNumbers(list1.getRoot(), list2.getRoot());

        LinkedList<String> list = new LinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for (String value : list) {
            System.out.println("value = " + value);
        }

        list.deleteNode("x");

        // deleting last and middle element
        list.deleteNode("d");

        for (String value : list) {
            System.out.println("value = " + value);
        }

        //deleting first element
        list.deleteNode("a");

        for (String value : list) {
            System.out.println("value = " + value);
        }

        Node root = list.getRoot();
        list.print(root.getNext());


//        String x = list.get(1);
//        x = list.get(2);
//        x = list.get(3);
//        x = list.get(4);

        LinkedList.Node<String> node = new Node<>("blah");
        System.out.println(node.getData());


        Node<String> nodeHead = new Node<>("a");

        nodeHead.setNext(new Node("b"));
        nodeHead.getNext().setNext(new Node("c"));
        nodeHead.getNext().getNext().setNext(new Node("d"));
        nodeHead.getNext().getNext().getNext().setNext(new Node("e"));

        list.deleteMiddleNode(nodeHead);



        Node<String> reverse = list.reverse(nodeHead);
        System.out.println(reverse);

    }

}
