/**
 * Created by darwinmorales on 17/08/2016.
 */
public class Threads {

    public static void main(String[] args) throws InterruptedException {
        final Thread separateThread = new Thread(new ThreadPrinter());
        separateThread.start();
        for (int i=0; i<5; i++ ){
            System.out.println("From the thread: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
        final Thread separateThread2 = new Thread(new ThreadPrinter());
        separateThread2.start();
    }

    private static class ThreadPrinter implements Runnable {
        @Override
        public void run() {
            for (int i=0; i<5; i++ ){
                System.out.println("From the thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
