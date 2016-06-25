package tk.unnikked.booleanevaluator.ast;

import tk.unnikked.booleanevaluator.ast.nonterminal.And;
import tk.unnikked.booleanevaluator.ast.nonterminal.Not;
import tk.unnikked.booleanevaluator.ast.nonterminal.Or;
import tk.unnikked.booleanevaluator.ast.terminal.False;
import tk.unnikked.booleanevaluator.ast.terminal.True;

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
