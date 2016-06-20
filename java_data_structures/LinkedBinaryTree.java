package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 20/06/2016.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {


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
