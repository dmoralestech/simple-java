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

    public static void main(String[] args) {
        final HolderNaive holder = new HolderNaive();
        System.out.println("deffering heavy creation..");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());
    }
}
