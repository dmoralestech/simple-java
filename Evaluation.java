import java.util.function.Supplier;

/**
 * Created by dmorales on 4/12/2015.
 */
public class Evaluation {
    public static boolean evaluate(final int value) {
        System.out.println("evaluating... " + value);
        simulateTimeConsumingOp(2000);
        return value > 100;
    }

    public static void simulateTimeConsumingOp(final int milliseconds) {
        try {
            Thread.sleep((milliseconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void eagerEvaluator(final boolean input1, final boolean input2) {
        System.out.println("eagerEvaluator called ...");
        System.out.println("accept: " + (input1 && input2));
    }

    public static void lazyEvaluator(final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
        System.out.println("lazy evaluator called...");
        System.out.println("accept: " + (input1.get() && input2.get()));
    }

    public static void main(String[] args) {
        System.out.println("//start: eager");
        eagerEvaluator(evaluate(1), evaluate(2));
        System.out.println("//end: eager");

        System.out.println("//start: lazy");
        lazyEvaluator(() -> evaluate(1), () -> evaluate(2));
        System.out.println("//end: lazy");

    }
}
