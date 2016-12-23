package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmorales on 21/12/2016.
 */
public class ArrayMultiplication {


    public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
        final int sign = num1.get(0) < 0 ^ num2.get(0) < 0 ? -1 : 1;
        num1.set(0, Math.abs(num1.get(0)));
        num2.set(0, Math.abs(num2.get(0)));

        List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
        for (int i = num1.size() - 1; i >= 0; --i) {
            for (int j = num2.size() - 1; j >= 0; --j) {
                int temp = result.get(i + j + 1) + num1.get(i) * num2.get(j);
                result.set(i + j + 1, temp);
                temp = result.get(i + j) + result.get(i + j + 1) / 10;
                result.set(i + j, temp);
                temp = result.get(i + j + 1) % 10;
                result.set(i + j + 1, temp);
            }
        }

        // Remove the leading zeroes.
        int first_not_zero = 0;
        while (first_not_zero < result.size() && result.get(first_not_zero) == 0) {
            ++first_not_zero;
        }
        result = result.subList(first_not_zero, result.size());
        if (result.isEmpty()) {
            return Arrays.asList(0);
        }
        result.set(0, result.get(0) * sign);
        return result;
    }

    public static List<Integer> addition(List<Integer> num1, List<Integer> num2) {
        final boolean isNum1Longer = num1.size() >= num2.size() ? true : false;

        List<Integer> longerList;
        List<Integer> shorterList;


        if (isNum1Longer) {
            longerList = num1;
            shorterList = num2;
        } else {
            longerList = num2;
            shorterList = num1;
        }

        shorterList = fillShorterList(shorterList, longerList.size());

        List<Integer> result = new ArrayList<>(Collections.nCopies(longerList.size() + 1, 0));
        for (int i = longerList.size() - 1; i >= 0; --i) {
            int temp = result.get(i + 1) + longerList.get(i) + (shorterList.get(i) == null ? 0 : shorterList.get(i));
            if (temp > 9) {
                result.set(i + 1, temp - 10);
                result.set(i, 1);
            } else {
                result.set(i + 1, temp);
            }
        }

        // Remove the leading zeroes.
        int first_not_zero = 0;
        while (first_not_zero < result.size() && result.get(first_not_zero) == 0) {
            ++first_not_zero;
        }
        result = result.subList(first_not_zero, result.size());
        if (result.isEmpty()) {
            return Arrays.asList(0);
        }
        return result;
    }

    private static List<Integer> fillShorterList(List<Integer> shorterList, int size) {
        List<Integer> tempList;
        tempList = new ArrayList<>(Collections.nCopies(size, 0));
        int startIndex = size - shorterList.size();
        for (int i = 0; i <= shorterList.size() - 1; i++) {
            tempList.set(i + startIndex, shorterList.get(i));
        }
        shorterList = tempList;
        return shorterList;
    }


    public static void main(String[] args) {
        multiply(Arrays.asList(7, 3), Arrays.asList(-3));
        addition(Arrays.asList(3), Arrays.asList(7, 3));
    }

}
