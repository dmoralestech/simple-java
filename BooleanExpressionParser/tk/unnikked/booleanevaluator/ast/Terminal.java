package tk.unnikked.booleanevaluator.ast;

import java.util.function.Predicate;

public abstract class Terminal implements BooleanExpression{
	protected Predicate<String> value;

	public Terminal(Predicate<String> value) {
		this.value = value;
	}

	public String toString() {
		return String.format("%s", value);
	}
}
