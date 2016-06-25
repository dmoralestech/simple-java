package tk.unnikked.booleanevaluator.lexer;

import java.io.*;

public class Lexer {
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

	public Lexer(InputStream in) {
		Reader r = new BufferedReader(new InputStreamReader(in));
		input = new StreamTokenizer(r);

		input.resetSyntax();
		input.wordChars('a', 'z');
		input.wordChars('A', 'Z');
		input.whitespaceChars('\u0000', ' ');
		input.whitespaceChars('\n', '\t');

		input.ordinaryChar('(');
		input.ordinaryChar(')');
		input.ordinaryChar('&');
		input.ordinaryChar('|');
		input.ordinaryChar('!');
		input.ordinaryChar('=');
	}

	public int nextSymbol() {
		try {
			switch (input.nextToken()) {
				case StreamTokenizer.TT_EOL:
					symbol = EOL;
					break;
				case StreamTokenizer.TT_EOF:
					symbol = EOF;
					break;
				case StreamTokenizer.TT_WORD: {
					if (input.sval.equalsIgnoreCase(TRUE_LITERAL)) symbol = TRUE;
					else if (input.sval.equalsIgnoreCase(FALSE_LITERAL)) symbol = FALSE;
					else if (input.sval.equalsIgnoreCase(PARTNO_LITERAL)) symbol = PARTNO;
					break;
				}
				case '(':
					symbol = LEFT;
					break;
				case ')':
					symbol = RIGHT;
					break;
				case '&':
					symbol = AND;
					break;
				case '|':
					symbol = OR;
					break;
				case '!':
					symbol = NOT;
					break;
				default:
					symbol = INVALID;
			}
		} catch (IOException e) {
			symbol = EOF;
		}

		return symbol;
	}

	public String toString() {
		return input.toString();
	}

	public static void main(String[] args) {
		String expression = "true & ((true | false) & !(true & false))";
		 expression = "PARTNO = '2324%'";
		Lexer l = new Lexer(new ByteArrayInputStream(expression.getBytes()));
		int s;
		while ( (s = l.nextSymbol()) != Lexer.EOF)
            if(s != EOL)
                System.out.printf("%s -> %s\n", l, s);
	}

    /*
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("PARTDESC|PARTNO|PNC|INVOICEDESC|SUNDRYCODE", 1);
        tokenizer.add("\\(", 2);
        tokenizer.add("\\)", 3);
        tokenizer.add("\\=|LIKE", 4);
        tokenizer.add("AND|OR", 5);
        tokenizer.add("[0-9]+", 6);
        tokenizer.add("'[%][0-9]+[%]'", 6);
        tokenizer.add("\\'(.*?)\\'", 8);
     */
    /*
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

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
        tokens = new LinkedList<Token>();
    }

    public void add(String regex, int token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

    public void tokenize(String str) {
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
            if (!match) throw new ParserException("Unexpected character in input: " + s);
        }
    }

    public LinkedList<Token> getTokens() {
        return tokens;
    }
     */
}
