package epi;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmorales on 23/12/2016.
 */
public class JumpGame {

    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int furthestReachSoFar = 0, lastIndex = maxAdvanceSteps.size() - 1;
        for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIndex; ++i) {
            furthestReachSoFar = Math.max(furthestReachSoFar, i + maxAdvanceSteps.get(i));
        }
        return furthestReachSoFar >= lastIndex;
    }

    public static void main(String[] args) {
        canReachEnd(Arrays.asList(3, 3, 1, 0, 2, 0, 1));
        canReachEnd(Arrays.asList(3, 32, 0, 0, 2, 0, 1));
    }
}
