package Bits;

/**
 * Created by darwinmorales on 10/09/2016.
 */
public class BitsUtils {


    public static long swapBits(long x, int i, int j) {

        if (((x >>> i) & 1) != ((x >>> j) & 1)) {
            long bitMask = (1L << i) | (1L << j);
            x ^= bitMask;
        }
        return x;

    }

    public static void main(String[] args) {

        System.out.println(swapBits(4, 2, 0));

    }
}
