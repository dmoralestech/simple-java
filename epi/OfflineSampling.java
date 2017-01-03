package epi;

/**
 * Created by darwinmorales on 3/01/2017.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OfflineSampling {
    // @include
    public static void randomSampling(int k, List<Integer> A) {
        Random gen = new Random();
        for (int i = 0; i < k; ++i) {
            // Generate a random int in [i, A.size() - 1].
            Collections.swap(A, i, i + gen.nextInt(A.size() - i));
        }
    }
    // @exclude

    public static void main(String[] args) {
        int n = 10, k = 4;
        List<Integer> A = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            A.add(i);
        }
        System.out.println(n + " " + k);

        randomSampling(k, A);
    }
}