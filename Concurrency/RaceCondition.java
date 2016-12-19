package Concurrency;

/**
 * Created by darwinmorales on 26/09/2016.
 */
public class RaceCondition {
    public static void main(String args[]) {
        UseCounter c = new UseCounter();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        Thread t3 = new Thread(c);
//        System.out.println("starting thread 1");
        t1.start();
//        System.out.println("starting thread 2");
        t2.start();
//        System.out.println("starting thread 3");
        t3.start();
    }

}
