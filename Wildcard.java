import java.util.List;

/**
 * Created by dmorales on 19/07/2016.
 */

public class Wildcard {

    private static class MyNode<E> {
        private E data;
        private MyNode next;

        private MyNode(E data) {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public MyNode getNext() {
            return next;
        }

        public void setNext(MyNode next) {
            this.next = next;
        }
    }


    public static boolean wildCardMatch(String text, String pattern) {
        // Create the cards by splitting using a RegEx. If more speed
        // is desired, a simpler character based splitting can be done.
        //Iterable<String> cards = Splitter.on('%').split(pattern);
        String[] cards = pattern.split("%");

        // Iterate over the cards.
        for (int i = 0; i < cards.length; i++) {

            int idx = text.indexOf(cards[i]);

            if (pattern.indexOf("%") > 0 && i == 0) {
                if (idx > 0) return false;
            }

            // Card not detected in the text.
            if (idx == -1) {
                return false;
            }

            // Move ahead, towards the right of the text.
            text = text.substring(idx + cards[i].length());
        }

        return true;
    }

    public static boolean isMatch(String s, String p) {
        int searchTextLength = s.length(), patternLength = p.length();
        int numOfWildCards = 0;
        for (int i = 0; i < patternLength; i++) {
            if (p.charAt(i) == '%') numOfWildCards++;
        }
        if (numOfWildCards == 0 && searchTextLength != patternLength) return false;
        else if (patternLength - numOfWildCards > searchTextLength) return false;

        boolean[] match = new boolean[searchTextLength + 1];
        match[0] = true;
        for (int i = 0; i < searchTextLength; i++) {
            match[i + 1] = false;
        }
        for (int i = 0; i < patternLength; i++) {
            if (p.charAt(i) == '%') {
                for (int j = 0; j < searchTextLength; j++) {
                    match[j + 1] = match[j] || match[j + 1];
                }
            } else {
                for (int j = searchTextLength - 1; j >= 0; j--) {
                    match[j + 1] = (p.charAt(i) == s.charAt(j)) && match[j];
                }
                match[0] = false;
            }
        }
        return match[searchTextLength];
    }

    public static boolean isMatch2(String s, String p) {
        int searchTextLength = s.length(), patternLength = p.length();
        boolean[][] match = new boolean[2][patternLength + 1];
        match[0][0] = true;
        for (int i = 0; i <= searchTextLength; i++) {
            for (int j = 0; j <= patternLength; j++) {
                if (j == 0) { // initialized first column
                    match[i % 2][j] = i == 0;
                    continue;
                }
                if (p.charAt(j - 1) == '%') {
                    match[i % 2][j] = (i > 0 && match[(i - 1) % 2][j]) || match[i % 2][j - 1];
                } else {
                    match[i % 2][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') && match[(i - 1) % 2][j - 1];
                }

            }
        }
        return match[searchTextLength % 2][patternLength];
    }

    public static void main(String[] args) {
        System.out.println(isMatch2("INCLUDES:- REPLACED ENGINE OIL & OIL FILTER.", "REPLACED%ENGINE%OIL%")); //false
        System.out.println(isMatch2("INCLUDES:- REPLACED ENGINE OIL & OIL FILTER.", "%REPLACED%ENGINE%OIL%")); //true
        System.out.println(isMatch2("WASHER BLAH", "WASHER%")); // true
        System.out.println(isMatch2("BLAH WASHER BLAH", "%WASHER%")); //true
        System.out.println(isMatch2("BLAH WASHER BLAH", "%WASHER")); //false
        System.out.println(isMatch2("RING BLAH WASHER BLAH", "RING%WASHER")); //false
        System.out.println(isMatch2("RINGNASDSADWASHER", "RING%WASHER")); //true

        MyNode<String> node = new MyNode<>("a");

        node.setNext(new MyNode("b"));
        node.getNext().setNext(new MyNode("c"));
        node.getNext().getNext().setNext(new MyNode("d"));


    }

}