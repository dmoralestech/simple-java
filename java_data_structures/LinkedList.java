package java_data_structures;

/**
 * Created by darwinmorales on 3/07/2016.
 */
public class LinkedList<E> {

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


}
