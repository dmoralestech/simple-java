import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by dmorales on 17/06/2016.
 */
public class IterableString implements Iterable<Character>, Iterator<Character> {

    private String str;
    private int count = 0;

    public IterableString(String s) {
        str = s;
    }

    @Override
    public Iterator<Character> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        if (count < str.length()) {
            return true;
        }
        return false;
    }

    @Override
    public Character next() {
        if (count == str.length()) {
            throw new NoSuchElementException();
        }
        count++;
        return str.charAt(count - 1);
    }

//    @Override
//    public void forEach(Consumer<? super Character> action) {
//        //throw new UnsupportedOperationException();
//    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Character> action) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        IterableString is = new IterableString("abcd");
        is.forEach(c -> System.out.println(c));

        Iterator<Character> c = is.iterator();
        while (c.hasNext()) {
            System.out.println(c.next());
        }

        for (char ch : is) {
            System.out.println(ch);
        }

    }
}
