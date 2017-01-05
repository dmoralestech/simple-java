package epi;

/**
 * Created by darwinmorales on 4/01/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OnlineSampling {
    // @include
    // Returns a random k-sized subset of {0, 1, ..., n - 1}.
    public static List<Integer> randomSubset(int n, int k) {
        Map<Integer, Integer> changedElements = new HashMap<>();
        Random randIdxGen = new Random();
        for (int i = 0; i < k; ++i) {
            // Generate random int in [i, n - 1].
            int bound = n - i;
            int randIdx = i + randIdxGen.nextInt(bound);
            Integer valueFromRandomNumber = changedElements.get(randIdx);
            Integer valueFromIndex = changedElements.get(i);
            if (valueFromRandomNumber == null && valueFromIndex == null) {
                changedElements.put(randIdx, i);
                changedElements.put(i, randIdx);
            } else if (valueFromRandomNumber == null && valueFromIndex != null) {
                changedElements.put(randIdx, valueFromIndex);
                changedElements.put(i, randIdx);
            } else if (valueFromRandomNumber != null && valueFromIndex == null) {
                changedElements.put(i, valueFromRandomNumber);
                changedElements.put(randIdx, i);
            } else {
                changedElements.put(i, valueFromRandomNumber);
                changedElements.put(randIdx, valueFromIndex);
            }
        }

        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; ++i) {
            result.add(changedElements.get(i));
        }
        return result;
    }
    // @exclude

    public static void main(String[] args) {
        int n, k;
        n = 5;
        k = 5;

        List<Integer> res = randomSubset(n, k);
    }
}