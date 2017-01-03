package epi;

/**
 * Created by darwinmorales on 3/01/2017.
 */
public class Timer {
    public static void main(String[] args) {
        int hour = 3;
        int min = 0;

        do {
            System.out.println(String.format("%02d : %02d", hour, min));
            min--;
            if (min < 0) {
                min = 59;
                hour--;
            }
        } while (hour >= 0);
    }
}
