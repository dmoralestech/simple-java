package java_data_structures;

/**
 * Created by darwinmorales on 23/07/2016.
 */
public class TreeEx<E> {

    public static class Node<E> {

        E data;
        Node left;
        Node right;

        public Node(E data) {
            this.data = data;
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void traverse(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData());
        traverse(root.getLeft());
        traverse(root.getRight());
    }

    public static void main(String[] args) {
        Node<String> node = new Node<>("a");
        node.setLeft(new Node("b"));
        node.setRight(new Node("c"));

        Node<String> left = node.getLeft();
        Node<String> right = node.getRight();

        left.setLeft(new Node("d"));
        left.setRight(new Node("e"));

        right.setLeft(new Node("f"));
        right.setRight(new Node("g"));

        traverse(node);
    }
}
