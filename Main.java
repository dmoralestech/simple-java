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


  }
}
