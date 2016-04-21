import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collector.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {

    static class Developer {

        private String name;
        private Set<String> languages;

        public Developer(String name) {
            this.languages = new HashSet<>();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Set<String> getLanguages() {
            return languages;
        }

        public void add(String language) {
            this.languages.add(language);
        }


    }

    public static void main(String[] args) {
        System.out.println("Hi");

        List<String> words = Arrays.asList("Darwin", "Nova", "Daniel", "Felicity");

        List<String> res = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                        //.forEach(System.out::println);
                .collect(Collectors.toList());

        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        System.out.println(num1.stream().mapToInt(i -> i).sum());

        List<Integer[]> pairs = num1.stream()
                .flatMap(i -> num2.stream().map(j -> new Integer[]{i, j}))
                .collect(Collectors.toList());

        Stream<List<Integer>> integerListStream = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6, 7, 8));

        Stream<Integer> integerStream = integerListStream.flatMap(Collection::stream);

        integerStream.forEach(System.out::println);

        List<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter (b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}))
                .collect(Collectors.toList());


        IntStream.rangeClosed(1, 100)
                .boxed()
                .filter(x -> (x % 3 == 0 || x % 5 == 0))
                .map( x -> x  + ": " + (x % 3 == 0 ? "Fizz" : "") + (x % 5 == 0 ? "Buzz" : ""))
                .forEach(System.out::println);


        List<Developer> team = new ArrayList<>();
        Developer poly = new Developer("a1");
        poly.add("clojure");
        poly.add("scala");
        poly.add("groovy");
        poly.add("go");

        Developer polyOldSchool = new Developer("a1");
        polyOldSchool.add("pascal");
        polyOldSchool.add("c");
        polyOldSchool.add("assembly");
        polyOldSchool.add("fortran");

        team.add(poly);
        team.add(polyOldSchool);

        List<String> teamLanguages = team.stream()
                .map(developer -> developer.getLanguages())
                .flatMap( list -> list.stream())
                .collect(Collectors.toList());


        List<Set<String>> setOfLangs = team.stream()
            .map(developer -> developer.getLanguages())
            .collect(Collectors.toList());

        teamLanguages.stream().forEach(System.out::println);

        boolean isThereAVBProgrammerInTheTeam =  teamLanguages.stream().noneMatch(language -> language.equalsIgnoreCase("VB"));

        Stream<Developer> streamOfDevelopers = Stream.of(new Developer("a"), new Developer("b"));

        streamOfDevelopers.peek(developer -> developer.add("Javascript"))
                .peek(developer -> developer.add("C++"))
                .peek(developer -> developer.add("Java"))
                .forEach(developer -> System.out.print(developer.getName() + " " + developer.getLanguages() + "\n"));

        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {

        }

        Stream.iterate(0, n -> n + 2)
                .skip(100)
                .limit(20)
                .forEach(System.out::println);

        UnaryOperator<int[]> s = new UnaryOperator<int[]>() {
            @Override
            public int[] apply(int[] t) {
                return new int[]{ t[1], t[0] + t[1]};
            }
        };
        int[] qq = new int[]{11, 21, 2, 3};
        int[] qq2 =  s.apply(qq);

        List<Integer> qq3 = Stream.iterate( new int[]{0,1},
                t -> new int[]{ t[1], t[0] + t[1] } )
                .limit(20)
                .map( t -> t[0])
                .collect(Collectors.toList());

                //.forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

        Supplier<Integer>  intSupplier = new Supplier<Integer>() {

            private int previous = 0;
            private int current = 1;

            public Integer get() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

    }
}
