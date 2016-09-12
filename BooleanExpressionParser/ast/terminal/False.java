package BooleanExpressionParser.ast.terminal;

import BooleanExpressionParser.ast.Terminal;

import java.util.function.Predicate;

public class False extends Terminal {
	public False(Predicate<String> value) {
		super(value);
	}

	public Predicate<String> interpret( ) {
		return value;
	}
}
