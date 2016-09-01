package java_concurrency;

/**
 * Created by dmorales on 1/09/2016.
 */
public class CheckResults {
    private static int counter = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i=0; i < 50; i++) {
                CheckResults.counter++;
                System.out.println("counter: " + CheckResults.counter);
            }
        }).start();

        while (CheckResults.counter < 10) {
            System.out.println("Not reached yet.");
        }

        System.out.println("Reached!");
    }
}
