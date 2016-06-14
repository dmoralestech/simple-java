package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 15/06/2016.
 */
public interface Tree<E> extends Iterable<E> {
    Position<E> root();
    Position<E> parent(Position<E> p);
    Iterable<Position<E>> children(Position<E> p);
    int numChildren(Position<E> p);
    boolean isInternal(Position<E> p);
    boolean isExternal(Position<E> p);
    boolean isRoot(Position<E> p);
    int size();
    boolean isEmpty();
    Iterator<E> iterator();
    Iterable<Position<E>> positions();
}
