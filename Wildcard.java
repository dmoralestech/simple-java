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

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '%') count++;
        }
        if (count==0 && m != n) return false;
        else if (n - count > m) return false;

        boolean[] match = new boolean[m+1];
        match[0] = true;
        for (int i = 0; i < m; i++) {
            match[i+1] = false;
        }
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '%') {
                for (int j = 0; j < m; j++) {
                    match[j+1] = match[j] || match[j+1];
                }
            } else {
                for (int j = m-1; j >= 0; j--) {
                    match[j+1] = (p.charAt(i) == '?' || p.charAt(i) == s.charAt(j)) && match[j];
                }
                match[0] = false;
            }
        }
        return match[m];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("INCLUDES:- REPLACED ENGINE OIL & OIL FILTER.", "REPLACED%ENGINE%OIL%")); //false
        System.out.println(isMatch("INCLUDES:- REPLACED ENGINE OIL & OIL FILTER.", "%REPLACED%ENGINE%OIL%")); //true
        System.out.println(isMatch("WASHER BLAH", "WASHER%")); // true
        System.out.println(isMatch("BLAH WASHER BLAH", "%WASHER%")); //true
        System.out.println(isMatch("BLAH WASHER BLAH", "%WASHER")); //false
        System.out.println(isMatch("RING BLAH WASHER BLAH", "RING%WASHER")); //false


    }

}