package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 20/06/2016.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
            this.element = element;
            this.parent = above;
            this.left = leftChild;
            this.right = rightChild;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    @Override
    public Position<E> root() {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        return null;
    }

    @Override
    public boolean isInternal(Position<E> p) {
        return false;
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return false;
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
