package BooleanExpressionParser.ast.nonterminal;

import BooleanExpressionParser.ast.NonTerminal;

import java.util.function.Predicate;

public class Or extends NonTerminal {
	public Predicate<String> interpret() {
		return left.interpret().or(right.interpret()) ;
	}

	public String toString() {
		return String.format("(%s | %s)", left, right);
	}

    public static void main(String[] args) {
        Predicate<String> pred1 = (String s) -> "'ABC'".indexOf(s) >= 0;
        boolean b = pred1.test("B");
        System.out.println("b = " + b);
    }
}
