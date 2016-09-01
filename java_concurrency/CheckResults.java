package java_concurrency;

/**
 * Created by dmorales on 1/09/2016.
 */
public class CheckResults {
    private static int counter = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i=0; i < 500; i++) {
                CheckResults.counter++;
            }
        }).start();

        while (CheckResults.counter < 100) {
            System.out.printf("Not reached yet.");
        }
        System.out.println("Reached!");
    }
}
