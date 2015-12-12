import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

class Main {
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
      List<Integer> num2 = Arrays.asList(3,4);

      List<Integer[]> pairs = num1.stream()
                                    .flatMap( i -> num2.stream().map(j -> new Integer[] {i, j}))
                                    .collect(Collectors.toList());

  }
}
