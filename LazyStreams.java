/**
 * Created by darwinmorales on 5/12/2015.
 */
public class LazyStreams {
    private static int length(final String name) {
        System.out.println("getting length for: " + name);
        return name.length();
    }

    private static String toUpper(final String name) {
        System.out.println("converting to uppercase: " + name);
        return name.toUpperCase();
    }

    public static void main(String[] args) {

    }

}
