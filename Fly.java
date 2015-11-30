/**
 * Created by darwinmorales on 28/11/2015.
 */
public interface Fly {
    default void takeOff() { System.out.println("Fly::TakeOff");}
    default void land() { System.out.println("Fly::land");}
    default void turn() { System.out.println("Fly::turn");}
    default void cruise() { System.out.println("Fly::cruise");}
}

//interface FastFly extends Fly {
//    default void takeOff { System.out.println("Fastfly::takeOff");}
//}
