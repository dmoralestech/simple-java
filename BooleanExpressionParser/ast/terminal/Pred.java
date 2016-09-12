package BooleanExpressionParser.ast.terminal;

import BooleanExpressionParser.ast.Terminal;

import java.util.function.Predicate;

/**
 * Created by darwinmorales on 24/06/2016.
 */
public class Pred extends Terminal {
    public Pred(Predicate<String> value) {
        super(value);
    }

    public Predicate<String> interpret( ) {
        return value;
    }
}