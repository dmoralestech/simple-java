package epi;

import java.util.List;

/**
 * Created by dmorales on 20/12/2016.
 */
public class DutchFlagPartition {

    public static enum Color { RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> A) {

        int smaller = 0, equal = 0, larger = A.size();

        Color pivot = A.get(pivotIndex);

        for (int i = 0; i < A.size() ; i++) {
            for (int j = 0; j < A.size() ; j++) {

            }
        }
    }
}
