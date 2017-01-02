package epi;

/**
 * Created by darwinmorales on 3/01/2017.
 */
public class Clock {
    public static void main(String[] args) {
        int hour = 12;
        int min = 0;
        do {
            if (min == 59) {
                min = 0;
                hour++;
            } else {
                min++;
            }
            if (hour == 13 && min == 0) {
                hour = 1;
            }
            System.out.println(String.format("%02d : %02d", hour, min));
        } while (true);

    }
}
