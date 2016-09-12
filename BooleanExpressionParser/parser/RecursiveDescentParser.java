package BooleanExpressionParser.parser;

import BooleanExpressionParser.ast.BooleanExpression;
import BooleanExpressionParser.ast.nonterminal.And;
import BooleanExpressionParser.ast.terminal.False;
import BooleanExpressionParser.ast.terminal.Pred;
import BooleanExpressionParser.lexer.Lexer2;
import BooleanExpressionParser.ast.nonterminal.Not;
import BooleanExpressionParser.ast.nonterminal.Or;
import BooleanExpressionParser.ast.terminal.True;

import java.util.function.Predicate;


public class RecursiveDescentParser {
	private Lexer2 lexer;
	private int symbol;
	private BooleanExpression root;
    private String temp = "ABC";

	private final True t = new True( (String s) -> s.indexOf(temp) > 0 );
	private final False f = new False((String s) -> s.indexOf(temp) > 0 );

	public RecursiveDescentParser(Lexer2 lexer) {
		this.lexer = lexer;
	}

	public BooleanExpression build() {
		expression();
		return root;
	}

	private void expression() {
		term();
		while (symbol == Lexer2.OR) {
			Or or = new Or();
			or.setLeft(root);
			term();
			or.setRight(root);
			root = or;
		}
	}

	private void term() {
		factor();
		while (symbol == Lexer2.AND) {
			And and = new And();
			and.setLeft(root);
			factor();
			and.setRight(root);
			root = and;
		}
	}

	private void factor() {
		symbol = lexer.nextSymbol();
		if (symbol == Lexer2.ATTRIBUTES) {
            symbol = lexer.nextSymbol();
            if (symbol == Lexer2.EQUALITY) {
                symbol = lexer.nextSymbol();
                if (symbol == Lexer2.PATTERN_VALUE) {
                    String tempValue = lexer.getCurrentValue();
                    Predicate<String> predTemp = (String s) -> tempValue.indexOf(s) >= 0 ;
                    root = new Pred(predTemp) ;
                    symbol = lexer.nextSymbol();
                }
            }
        } else if (symbol == Lexer2.TRUE) {
			root = t;
			symbol = lexer.nextSymbol();
		} else if (symbol == Lexer2.FALSE) {
			root = f;
			symbol = lexer.nextSymbol();
		} else if (symbol == Lexer2.NOT) {
			Not not = new Not();
			factor();
			not.setChild(root);
			root = not;
		} else if (symbol == Lexer2.LEFT) {
			expression();
			symbol = lexer.nextSymbol(); // we don't care about ')'
		} else {
			throw new RuntimeException("Expression Malformed");
		}
	}
}
