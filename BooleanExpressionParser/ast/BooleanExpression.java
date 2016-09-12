package BooleanExpressionParser.ast;

import java.util.function.Predicate;

/**
 * <expression>::=<term>{<or><term>}
 * <term>::=<factor>{<and><factor>}
 * <factor>::=<constant>|<not><factor>|(<expression>)
 * <constant>::= false|true
 * <or>::='|'
 * <and>::='&'
 * <not>::='!'
 */
public interface BooleanExpression {
	public Predicate<String> interpret();
}
