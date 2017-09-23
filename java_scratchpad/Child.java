package java_scratchpad;

import javafx.scene.Parent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.util.*;

/**
 * Created by darwinmorales on 17/06/2017.
 */
 class Review {


protected  static  int count = 0;

    public Review() {
        super();
    }
    static int getCount() {return count; }
}

public class Child extends Review {
    public Child() {
        System.out.println("hi");
    }

    public static void main(String[] args) {


        int x = 0, y = 1, z = 4;

        if (x > 0)
             if (y < 3 )
                 System.out.printf("c");
             else if ( y < 4)
                 System.out.println("y");
        else
                 System.out.println("For");


         Double d = null;



    }
}