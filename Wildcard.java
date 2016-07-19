import java.util.List;

/**
 * Created by dmorales on 19/07/2016.
 */

public class Wildcard {


    public static boolean wildCardMatch(String text, String pattern) {
        // Create the cards by splitting using a RegEx. If more speed
        // is desired, a simpler character based splitting can be done.
        //Iterable<String> cards = Splitter.on('%').split(pattern);
        String[] cards = pattern.split("%");

        // Iterate over the cards.
        for (int i = 0; i < cards.length; i++) {

            int idx = text.indexOf(cards[i]);

            if (pattern.indexOf("%") > 0 && i == 0) {
                if (idx > 0 ) return false;
            }

            // Card not detected in the text.
            if(idx == -1) {
                return false;
            }

            // Move ahead, towards the right of the text.
            text = text.substring(idx + cards[i].length());
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(wildCardMatch("INCLUDES:- REPLACED ENGINE OIL & OIL FILTER.", "REPLACED%ENGINE%OIL%"));
        System.out.println(wildCardMatch("INCLUDES:- REPLACED ENGINE OIL & OIL FILTER.", "%REPLACED%ENGINE%OIL%"));
        System.out.println(wildCardMatch("WASHER BLAH", "WASHER%"));
        System.out.println(wildCardMatch("BLAH WASHER BLAH", "%WASHER%"));


    }

}