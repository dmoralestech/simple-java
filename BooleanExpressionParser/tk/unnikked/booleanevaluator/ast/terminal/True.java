package tk.unnikked.booleanevaluator.ast.terminal;

import tk.unnikked.booleanevaluator.ast.Terminal;

import java.util.function.Predicate;

public class True extends Terminal {
	public True(Predicate<String> value) {
		super(value);
	}

	public Predicate<String> interpret() {
		return value;
	}


}
