package Concurrency;

/**
 * Created by darwinmorales on 26/09/2016.
 */
class UseCounter implements Runnable {
    public void increment() {
// increments the counter and prints the value // of the counter shared between threads Counter.count++; System.out.print(Counter.count + " ");
    }

    public void run() {
        increment();
        increment();
        increment();
    }
}