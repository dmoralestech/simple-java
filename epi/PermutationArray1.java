package epi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by darwinmorales on 30/12/2016.
 */
public class PermutationArray1 {
    // @include
    public static void applyPermutation(List<Integer> perm, List<Integer> A) {
        for (int i = 0; i < A.size(); ++i) {
            // Check if the element at index i has not been moved by checking if
            // perm.get(i) is nonnegative.
            int next = i;
            while (perm.get(next) >= 0) {
                Collections.swap(A, i, perm.get(next));
                int temp = perm.get(next);
                // Subtracts perm.size() from an entry in perm to make it negative,
                // which indicates the corresponding move has been performed.
                perm.set(next, perm.get(next) - perm.size());
                next = temp;
            }
        }

        // Restore perm.
        for (int i = 0; i < perm.size(); i++) {
            perm.set(i, perm.get(i) + perm.size());
        }
    }

    public static void printCyclicPermutation(List<Integer> A) {
        for (int i = 0; i < A.size(); i++) {
            printArrayStartingAt(A, i);
        }
    }

    public static void printArrayStartingAt(List<Integer> A, int index) {
        for (int i = 0; i < A.size(); i++) {
            int temp = index + i;

            System.out.print(A.get(i));
        }
    }

    public static void main(String[] args) {
        applyPermutation(Arrays.asList(2, 0, 1, 3), Arrays.asList(0, 1, 2, 3));
        applyPermutation(Arrays.asList(0, 1, 2, 3), Arrays.asList(10, 11, 12, 13));
    }

}



