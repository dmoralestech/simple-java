package tokenizer;

/**
 * Created by darwinmorales on 24/06/2016.
 */

import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer2 {
    private StreamTokenizer input;

    private int symbol = NONE;
    public static final int EOL = -3;
    public static final int EOF = -2;
    public static final int INVALID = -1;

    public static final int NONE = 0;

    public static final int OR = 1;
    public static final int AND = 2;
    public static final int NOT = 3;

    public static final int TRUE = 4;
    public static final int FALSE = 5;

    public static final int LEFT = 6;
    public static final int RIGHT = 7;
    public static final int PARTNO = 8;

    public static final String TRUE_LITERAL = "true";
    public static final String FALSE_LITERAL = "false";
    public static final String PARTNO_LITERAL = "PartNo";

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;
    private int counter = 0;
    private int indexToken = 0;

    private class TokenInfo {
        public final Pattern regex;
        public final int token;

        public TokenInfo(Pattern regex, int token) {
            super();
            this.regex = regex;
            this.token = token;
        }
    }

    public class Token {
        public final int token;
        public final String sequence;

        public Token(int token, String sequence) {
            super();
            this.token = token;
            this.sequence = sequence;
        }

    }

    public Lexer2(String str) {
        tokenInfos = new LinkedList<>();
        tokens = new LinkedList<>();
        add("PARTDESC|PARTNO|PNC|INVOICEDESC|SUNDRYCODE", 1);
        add("\\(", 2);
        add("\\)", 3);
        add("\\=|LIKE", 4);
        add("AND|OR", 5);
        add("[0-9]+", 6);
        add("'[%][0-9]+[%]'", 6);
        add("\\'(.*?)\\'", 8);
        String s = str.trim();
        tokens.clear();
        while (!s.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(s);
                if (m.find()) {
                    match = true;
                    String tok = m.group().trim();
                    s = m.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok));
                    break;
                }
            }
            if (!match)
                System.out.println("Unexpected character in input: " + s);
        }
    }

    private void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

    public int nextSymbol() {
        int res;
        if (counter < tokens.size()) {
            res = tokens.get(counter).token;
            indexToken = counter;
        } else {
            res = -1;
        }

        counter++;
        return res;
    }

    public String toString() {
        return tokens.get(indexToken).sequence;
    }

    public static void main(String[] args) {
        String expression = "true & ((true | false) & !(true & false))";
        expression = "PARTNO LIKE '2324%'";
        Lexer2 l = new Lexer2(expression);
        int s;
        while ( (s = l.nextSymbol()) > 0 )
            if(s > 0)
                System.out.printf("%s %s \n", l, s);
    }

}

