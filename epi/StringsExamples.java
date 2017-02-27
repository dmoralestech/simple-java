package epi;

/**
 * Created by dmorales on 28/02/2017.
 */
public class StringsExamples {

    public static String convertToString(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
        }

        StringBuilder s = new StringBuilder();
        do {
            s.append((char)('0' + Math.abs(x % 10)));
            x /= 10;
        } while (x != 0);

        if (isNegative) {
            s.append('-'); // Adds the negative sign back.
        }
        s.reverse();
        return s.toString();
    }

    public static int convertToInt(String s) {
        return 0;
    }

    public static void main(String[] args) {
        convertToString(314);
    }
}
