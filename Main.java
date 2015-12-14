import java.util.*;
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

        List<Developer> team = new ArrayList<>();
        Developer poly = new Developer("a1");
        poly.add("clojure");
        poly.add("scala");
        poly.add("groovy");
        poly.add("go");

        Developer polyOldSchool = new Developer("a1");
        poly.add("pascal");
        poly.add("c");
        poly.add("assembly");
        poly.add("fortran");

        team.add(poly);
        team.add(polyOldSchool);

        List<String> teamLanguages = team.stream()
                .map(developer -> developer.getLanguages())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        teamLanguages.stream().forEach(System.out::println);

        boolean isThereAVBProgrammerInTheTeam =  teamLanguages.stream().noneMatch(language -> language.equalsIgnoreCase("VB"));

        Stream<Developer> streamOfDevelopers = Stream.of(new Developer("a"), new Developer("b"));

        streamOfDevelopers.peek(developer -> developer.add("Javascript"))
                .peek(developer -> developer.add("C++"))
                .peek(developer -> developer.add("Java"))
                .forEach(developer -> System.out.print(developer.getName() + " " + developer.getLanguages() + "\n"));

    }
}
