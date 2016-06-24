package tokenizer;

public class Test {

    /**
     * @param args
     */
//    public static void main(String[] args) {
//        Tokenizer tokenizer = new Tokenizer();
//        tokenizer.add("sin|cos|exp|ln|sqrt", 1);
//        tokenizer.add("\\(", 2);
//        tokenizer.add("\\)", 3);
//        tokenizer.add("\\+|-", 4);
//        tokenizer.add("\\*|/", 5);
//        tokenizer.add("[0-9]+", 6);
//        tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 7);
//
//        try {
//            tokenizer.tokenize(" sin(x) * (1 - var_12) ");
//
//            for (Tokenizer.Token tok : tokenizer.getTokens()) {
//                System.out.println("" + tok.token + " " + tok.sequence);
//            }
//        } catch (ParserException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("PARTDESC|PARTNO|PNC|INVOICEDESC|SUNDRYCODE", 1);
        tokenizer.add("\\(", 2);
        tokenizer.add("\\)", 3);
        tokenizer.add("\\=|LIKE", 4);
        tokenizer.add("AND|OR", 5);
        tokenizer.add("[0-9]+", 6);
        tokenizer.add("'[%][0-9]+[%]'", 6);
        tokenizer.add("\\'(.*?)\\'", 8);
                    //   [(.*?)\]
        try {
            tokenizer.tokenize("(PARTDESC LIKE '%AIR%BOLT%')");

            for (Tokenizer.Token tok : tokenizer.getTokens()) {
                System.out.println("" + tok.token + " " + tok.sequence);
            }
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }

    }
}
