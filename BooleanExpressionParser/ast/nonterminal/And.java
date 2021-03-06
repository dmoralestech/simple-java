package BooleanExpressionParser.ast.nonterminal;

import BooleanExpressionParser.ast.NonTerminal;

import java.util.function.Predicate;

public class And extends NonTerminal {
	public Predicate<String> interpret() {
		return left.interpret().and(right.interpret()) ;
	}

	public String toString() {
		return String.format("(%s & %s)", left, right);
	}
}
