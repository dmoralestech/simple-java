import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by dmorales on 1/03/2016.
 */
public class CallableExample {

    public static class WordLengthCallable implements Callable {

        private String word;

        public WordLengthCallable(String word) {
            this.word = word;
        }

        @Override
        public Integer call() throws Exception {
            return Integer.valueOf(word.length());
        }
    }

    public static void main(String[] args) throws Exception {
        String[] args1 = {"erwerew", "dfgdfdfdfbv", "xcvxcvxcvcx"};
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Set<Future<Integer>> set = new HashSet<>();
        for (String word : args1) {
            Callable<Integer> callable = new WordLengthCallable(word);
            Future<Integer> future = pool.submit(callable);
            set.add(future);
        }

        int sum = 0;
        for (Future<Integer> future : set) {
            sum += future.get();
        }

        System.out.printf("The sum of lengths is %s %n", sum);
        System.exit(sum);
    }
}
