package java_data_structures;

/**
 * Created by dmorales on 18/06/2016.
 */
public abstract class AbstractBinaryTree<E>  implements BinaryTree<E>  {

    @Override
    public Position<E> left(Position<E> e) throws Exception {
        return null;
    }

    @Override
    public Position<E> right(Position<E> e) throws Exception {
        return null;
    }

    @Override
    public Position<E> sibling(Position<E> e) throws Exception {
        Position<E> parent = parent(e);
        if (parent == null) {
            return null;
        }
        if (e == left(parent)) {
            return right(parent);
        } else {
            return left(parent);
        }
    }
}
