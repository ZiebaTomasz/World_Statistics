package com.tom.world.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World_some {

  public static void main(String[] args) {

    String fileName = "D:/Courses/Web services/java-web-services/10 Developing Bottom Up Web Services/attached_files/063 The Project Setup/world/src/main/resources/txt/test.txt";

    Predicate<String> onlyNumbers = Pattern.compile("\\d+").asPredicate();

    //read file into stream, try-with-resources
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      List<String> collect = stream.collect(Collectors.toList());
      Stream<String> stringStream = collect.stream()
          .filter(Objects::nonNull)
          .filter(s -> s.length() > 3)
          .filter(onlyNumbers.negate())
          .flatMap(s -> Arrays.stream(s.split(" ")));

      List<String> filteredStrings = stringStream
          .filter(s -> !s.equals("-"))
          .filter(s -> s.length() > 5)
          .filter(s -> !s.contains("'"))
//          .forEach(s -> {
//            s = s.replaceAll(".","text ");
//            System.out.println(s);
//          });
          .map(World_some::filterStrings)
          .map(String::toLowerCase)
          .collect(Collectors.toList());

      Map<String, Long> longMap = filteredStrings.stream()
          .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

      longMap.forEach((s, aLong) -> System.out.println("world: "+ s + " occure " + aLong));

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static String filterStrings(String s) {
    return s.replaceAll("\\.", "").replaceAll(",", "").replaceAll("\\?", "")
        .replaceAll("\\(","")
        .replaceAll("\\)","")
        .replaceAll("\\[","")
        .replaceAll("]","")
        .replaceAll("!","")
        .replaceAll(":","")
        .replaceAll("\"", "")
        .replaceAll(":","");
  }
}
