package java_data_structures;

import java.util.Iterator;

/**
 * Created by darwinmorales on 16/06/2016.
 */
public interface List<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    E get(int i) throws Exception;

    E set(int i, E e) throws Exception;

    void add(int i, E e) throws Exception;

    void remove(int i) throws Exception;

    Iterator<E> iterator();
}
