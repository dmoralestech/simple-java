package java_data_structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by darwinmorales on 15/06/2016.
 */
public abstract class AbstractTree<E> implements Tree<E> {
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
    public Iterable<Position<E>> children(Position<E> p) {
        return null;
    }

    @Override
    public int numChildren(Position<E> p) {
        int count = 0;
        for(Position child: children(p)) {
            count++;
        }
        return count;
    }

    @Override
    public int size() {
        int count = 0;
        for(Position child: positions()) {
            count++;
        }
        return count;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preorder();
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


    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(p);
        for(Position<E> c : children(p)) {
            preorderSubtree(c, snapshot);
        }
    }


    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }


    private void postOrderSubTree(Position<E> p, List<Position<E>> snapshot) {
        for (Position<E> c: children(p)) {
            postOrderSubTree(c, snapshot);
        }
        snapshot.add(p);
    }

    public Iterable<Position<E>> postOrder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postOrderSubTree(root(), snapshot);
        }
        return snapshot;
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext() {
            return posIterator.hasNext();
        }
        public E next()  {
            try {
                return posIterator.next().getElement();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        public void remove() {
            posIterator.remove();
        }
    }
}
