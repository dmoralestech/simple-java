package BooleanExpressionParser.ast;

import BooleanExpressionParser.ast.nonterminal.And;
import BooleanExpressionParser.ast.terminal.False;
import BooleanExpressionParser.ast.nonterminal.Not;
import BooleanExpressionParser.ast.nonterminal.Or;
import BooleanExpressionParser.ast.terminal.True;

import java.util.function.Predicate;

public class Test {
	public static void main(String[] args) {
        Predicate<String> checkStarts = (String name) -> name.startsWith("Abc");
        Predicate<String> checkStarts2 = (String name) -> name.startsWith("efg");
		True t = new True(checkStarts);
		False f = new False(checkStarts2);

		Or or = new Or();
		or.setLeft(t);
		or.setRight(f);

		Not not = new Not();
		not.setChild(f);
		And and = new And();
		and.setLeft(or);
		and.setRight(not);

		BooleanExpression root = and;

		System.out.println(or);
		System.out.println(or.interpret().test("dar"));
	}
}
