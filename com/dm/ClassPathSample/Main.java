package com.dm.ClassPathSample;

/**
 * Created by dmorales on 15/04/2016.
 */
public class Main {
    public static void main(String[] args) {
        Helper helper = new Helper();
        System.out.println(helper.getMessage());

        Util util = new Util();
        System.out.println(util.getMessage());

    }
}
