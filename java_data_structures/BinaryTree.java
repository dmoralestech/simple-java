package java_data_structures;

/**
 * Created by dmorales on 18/06/2016.
 */
public interface BinaryTree<E> extends Tree<E> {
    Position<E> left(Position<E> e) throws Exception;
    Position<E> right(Position<E> e) throws Exception;
    Position<E> sibling(Position<E> e) throws Exception;
}
