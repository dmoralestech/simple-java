package epi;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmorales on 23/12/2016.
 */
public class ArrayJumpGame {

    public static boolean arrayJump(List<Integer> a, int numOfSteps){
        int currentIndex = 0;

        assert(a.get(0)== 0);

        while(currentIndex < a.size()) {
            currentIndex += numOfSteps;
            if (a.get(currentIndex) == 0) {

            } else {
                if (a.get(currentIndex) == 0) {

                }
            }

        }

        return true;

    }



    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(0, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1);

        arrayJump(a, 2);

    }

}
