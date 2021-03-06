package epi;

import java.util.ArrayList;
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

    public static void printArrayStartingAt(List<Integer> A, int start) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(A.size(), 0));
        for (int i = 0; i < A.size(); i++) {
            int newIndex = getNewIndex(i, start, A.size());
            result.set(newIndex, i);
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
        }
        System.out.println("");
    }

    public static int getNewIndex(int currentIndex, int startIndex, int sizeArray) {
        int newIndex = currentIndex + startIndex;
            // if the newIndex is over the size of the array, then go back to the beginning.
        if (newIndex >= sizeArray) {
            newIndex = newIndex - sizeArray;
        }
        if (newIndex >= sizeArray || newIndex < 0) {
            System.out.println("error");
        }
        return newIndex;
    }

    public static void main(String[] args) {
        //printArrayStartingAt(Arrays.asList(0, 1, 2, 3), 2);
        printCyclicPermutation(Arrays.asList(0, 1, 2, 3));
        applyPermutation(Arrays.asList(2, 0, 1, 3), Arrays.asList(0, 1, 2, 3));
        applyPermutation(Arrays.asList(0, 1, 2, 3), Arrays.asList(10, 11, 12, 13));
    }

}



