import java.util.function.Supplier;

/**
 * Created by dmorales on 2/12/2015.
 */
public class NestedClassesTest {

    private static class Heavy {
        public Heavy() {
            System.out.println("Heavy created");
        }

        public String toString() {
            return "quite heavy";
        }
    }

    private static class HolderNaive {
        private Heavy heavy;

        public HolderNaive() {
            System.out.println("Holder created");
        }

        public Heavy getHeavy() {
            if (heavy == null) {
                heavy = new Heavy();
            }
            return  heavy;
        }
    }

    private class Holder {
        private Supplier<Heavy> heavy  = () -> createAndCacheHeavy();

        public Holder() {
            System.out.println("Holder created");
        }

        public Heavy getHeavy() {
            return heavy.get();
        }

        private synchronized Heavy createAndCacheHeavy() {

            class HeavyFactory implements Supplier<Heavy> {
                private final Heavy heavyInstance = new Heavy();

                @Override
                public Heavy get() { return heavyInstance; }

            }

            if (!HeavyFactory.class.isInstance(heavy)) {
                heavy = new HeavyFactory();
            }

            return heavy.get();
        }

    }

    public static void main(String[] args) {
        final HolderNaive holder = new HolderNaive();
        System.out.println("deffering heavy creation..");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());
    }
}
