package epi;

import java.util.Arrays;

/**
 * Created by dmorales on 19/12/2016.
 */
public class ArrayEvenOdd {

    public static void evenOdd(int[] A) {
        int nextEven = 0, nextOdd = A.length - 1;

        while (nextEven < nextOdd) {
            if (A[nextEven] % 2 == 0) {
                nextEven++;
            } else {
                int temp = A[nextEven];
                A[nextEven] = A[nextOdd];
                A[nextOdd--] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {3, 5, 9, 10,20,30,40,50,60,70,80,90,100};

        evenOdd(A);
        //{100, 90, 80, 10,20,30,40,50,60,70,9,5,3};

        Arrays.sort(A);
        int index = Arrays.binarySearch(A, 3);

        int[] B = Arrays.copyOf(A, 3);
        int[] C = Arrays.copyOfRange(A, 3, 6);
        int[] D = Arrays.copyOfRange(A, 1, 10);

        Arrays.fill(D, 99);


    }
}
