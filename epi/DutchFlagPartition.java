package epi;

import java.util.*;

/**
 * Created by dmorales on 20/12/2016.
 */
public class DutchFlagPartition {

    public enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> A) {

        int smaller = 0, equal = 0, larger = A.size() - 1;

        Color pivot = A.get(pivotIndex);

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).ordinal() < pivot.ordinal()) {
                Collections.swap(A, smaller++, i);
            }
        }

        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); --i) {
            if (A.get(i).ordinal() > pivot.ordinal()) {
                Collections.swap(A, larger--, i);
            }
        }
    }

    public static void dutchFlagPartition2(List<Color> A) {

        int smaller = 0;

        for ( Color color : Color.values()) {
            for (int i = 0; i < A.size(); i++) {
                if (A.get(i).ordinal() == color.ordinal()) {
                    Collections.swap(A, smaller++, i);
                }
            }
        }

//
//        for (int i = smaller; i < A.size(); i++) {
//            if (A.get(i).ordinal() == Color.WHITE.ordinal()) {
//                Collections.swap(A, smaller++, i);
//            }
//        }
//
//        for (int i = smaller; i < A.size(); i++) {
//            if (A.get(i).ordinal() == Color.BLUE.ordinal()) {
//                Collections.swap(A, smaller++, i);
//            }
//        }

    }


    private static List<Color> randArray(int len) {
        Random r = new Random();
        List<Color> ret = new ArrayList<>(len);
        for (int i = 0; i < len; ++i) {
            ret.add(Color.values()[r.nextInt(3)]);
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Color> listColor = new ArrayList<>();

        listColor.add(Color.RED);
        listColor.add(Color.BLUE);
        listColor.add(Color.WHITE);
        listColor.add(Color.RED);
        listColor.add(Color.BLUE);
        listColor.add(Color.RED);
        listColor.add(Color.WHITE);
        listColor.add(Color.RED);
        listColor.add(Color.BLUE);
        listColor.add(Color.RED);
        listColor.add(Color.WHITE);

        dutchFlagPartition2(listColor);
        //dutchFlagPartition(2, listColor);



        Random gen = new Random();

        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = gen.nextInt(100) + 1;
            }

            List<Color> A = randArray(n);

            int pivotIndex = gen.nextInt(n);
            Color pivot = A.get(pivotIndex);

            dutchFlagPartition(pivotIndex, A);

            //Collections.sort(A, (o1, o2) ->  o2.ordinal() - o1.ordinal());

            int i = 0;
            while (i < n && A.get(i).ordinal() < pivot.ordinal()) {
                System.out.print(A.get(i) + " ");
                ++i;
            }
            while (i < n && A.get(i) == pivot) {
                System.out.print(A.get(i) + " ");
                ++i;
            }
            while (i < n && A.get(i).ordinal() > pivot.ordinal()) {
                System.out.print(A.get(i) + " ");
                ++i;
            }
            System.out.println();

            assert (i == n);
        }
    }
}
