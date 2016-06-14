package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 15/06/2016.
 */
public class AbstractTree<E> implements Tree<E> {
    @Override
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    @Override
    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    @Override
    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
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
    public Iterable<Position<E>> children(Position<E> p) {
        return null;
    }

    @Override
    public int numChildren(Position<E> p) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }

    public int depth(Position<E> p) {
        if (isRoot(p)) {
            return 0;
        } else {
            return 1 + depth(parent(p));
        }
    }

    private int heightBad() {
        int h = 0;
        for(Position<E> p: positions()) {
            if (isExternal(p)) {
                h = Math.max(h, depth(p));
            }
        }
        return h;
    }

    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c: children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }


}
