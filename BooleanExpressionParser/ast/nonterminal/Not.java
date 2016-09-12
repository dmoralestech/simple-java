package BooleanExpressionParser.ast.nonterminal;

import BooleanExpressionParser.ast.BooleanExpression;
import BooleanExpressionParser.ast.NonTerminal;

import java.util.function.Predicate;

public class Not extends NonTerminal {
	public void setChild(BooleanExpression child) {
		setLeft(child);
	}

	public void setRight(BooleanExpression right) {
		throw new UnsupportedOperationException();
	}

	public Predicate<String> interpret() {
		return left.interpret().negate();
	}

	public String toString() {
		return String.format("!%s", left);
	}
}
