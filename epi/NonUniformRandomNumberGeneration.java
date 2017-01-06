package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by darwinmorales on 6/01/2017.
 */
public class NonUniformRandomNumberGeneration {
    public static int nonuniformRandomNumberGeneration(
            List<Integer> values, List<Double> probabilities) {
        List<Double> prefixSumOfProbabilities = new ArrayList<>();
        prefixSumOfProbabilities.add(0.0);
        // Creating the endpoints for the intervals corresponding to the
        // probabilities.
        for (double p : probabilities) {
            prefixSumOfProbabilities.add(
                    prefixSumOfProbabilities.get(prefixSumOfProbabilities.size() - 1)
                            + p);
        }

        Random r = new Random();
        // Get a random number in [0.0,1.0).
        final double uniform01 = r.nextDouble();
        // Find the index of the interval that uniform01 lies in.
        int it = Collections.binarySearch(prefixSumOfProbabilities, uniform01);
        if (it < 0) {
            // We want the index of the first element in the array which is
            // greater than the key.
            //
            // When a key is not present in the array, Collections.binarySearch()
            // returns the negative of 1 plus the smallest index whose entry
            // is greater than the key.
            //
            // Therefore, if the return value is negative, by taking its absolute
            // value and adding 1 to it, we get the desired index.
            final int intervalIdx = (Math.abs(it) - 1) - 1;
            return values.get(intervalIdx);
        } else {
            // We have it >= 0, i.e., uniform01 equals an entry
            // in prefixSumOfProbabilities.
            //
            // Because we uniform01 is a random double, the probability of it
            // equalling an endpoint in prefixSumOfProbabilities is exceedingly low.
            // However, it is not 0, so to be robust we must consider this case.
            return values.get(it);
        }
    }
}
