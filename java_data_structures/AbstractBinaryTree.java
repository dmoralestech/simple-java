package java_data_structures;

import java.util.ArrayList;
import java.util.List;

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

    public int numChildren(Position<E> p) {
        int count = 0;
        try {
            if (left(p) != null) {
                count++;
            }
            if (right(p) != null) {
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);
        try {
            if (left(p) != null) {
                snapshot.add(left(p));
            }
            if (right(p) != null) {
                snapshot.add(right(p));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return snapshot;

    }
}
