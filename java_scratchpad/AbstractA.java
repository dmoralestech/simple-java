package java_scratchpad;

import java.util.Objects;

/**
 * Created by darwinmorales on 17/06/2017.
 */
abstract public class AbstractA {

    public int getI() {
        System.out.println( I1.name);
        return 6;
    }

    abstract int getD();

}

interface  I1 {
    String name = "QQ";

    int getA() throws Exception;

}
