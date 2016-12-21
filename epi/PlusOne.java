package epi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmorales on 21/12/2016.
 */
public class PlusOne {

    public static List<Integer> plusOne(List<Integer> A) {
        int n = A.size() - 1;
        A.set(n, A.get(n) + 1);
        for (int i = n; i > 0 && A.get(i) == 10; --i) {
            A.set(i, 0);
            A.set(i - 1, A.get(i - 1) + 1);
        }
        if (A.get(0) == 10) {
            // Need additional digit as the most significant digit (i.e., A.get(0))
            // has a carry-out.
            A.set(0, 0);
            A.add(0, 1);
        }
        return A;
    }

    public static List<Integer> plusX(List<Integer> A, int x) {
        int n = A.size() - 1;
        A.set(n, A.get(n) + x);
        for (int i = n; i > 0 && A.get(i) > 10; --i) {
            A.set(i, A.get(i) - 10);
            A.set(i - 1, A.get(i - 1) + 10);
        }
        if (A.get(0) == 10) {
            // Need additional digit as the most significant digit (i.e., A.get(0))
            // has a carry-out.
            A.set(0, 0);
            A.add(0, 1);
        }
        return A;
    }

    public static void main(String[] args) {
        List<Integer> result = plusOne(new ArrayList<Integer>() {
            {
                add(2);
                add(9);
                add(9);
            }
        });
        result = plusX(new ArrayList<Integer>() {
            {
                add(3);
                add(1);
                add(4);
            }
        }, 8);
    }

}
