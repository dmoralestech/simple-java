package BooleanExpressionParser;


import BooleanExpressionParser.ast.BooleanExpression;
import BooleanExpressionParser.lexer.Lexer2;
import BooleanExpressionParser.parser.RecursiveDescentParser;

import java.util.Scanner;

public class BooleanEvaluator {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner((System.in));
		String expression = "(true & ((true | false) & !(true & false)))";
//		expression = "(PARTNO = 'XYZ') OR ((PARTNO = '123') OR (PARTNO = 'ABC') AND (PARTNO = 'DEF')) ";
		expression = "((PARTNO = 'OOP') OR (PARTNO = 'XYZ')) AND ((PARTNO = 'CBA') OR (PARTNO = 'OOP')))";
		//expression = "(true) || (false) ";

		Lexer2 lexer = new Lexer2(expression);
		RecursiveDescentParser parser = new RecursiveDescentParser(lexer);
		BooleanExpression ast = parser.build();
		System.out.println(String.format("AST: %s", ast));
		System.out.println(String.format("RES: %s", ast.interpret().test("OOP")));
	}

}
