import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {

        //! Stream

        List<Person> people = getPeople();

        //* Count
        System.out.println("--- COUNT ---");
        long size = people.stream()
                .filter(person -> person.getGender().equals(Gender.MALE))
                .count();
        System.out.println(size);


        //* Map - Flatmap
        System.out.println("\n--- MAP ---");
        people.stream()
                .map(Person::getName)
                .mapToInt(String::length)
                .forEach(System.out::println);
        System.out.println("\n--- FLATMAP ---");
        people.stream()
                .flatMap(person -> person.getProgrammingLanguages().stream())
                .collect(Collectors.toSet())
                .forEach(System.out::println);


        //* Filter
        System.out.println("\n--- FILTER ---");
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        females.forEach(System.out::println);


        //* Sorted
        System.out.println("\n--- SORTED ---");
        people.stream()
//                .sorted()
//                .sorted(Comparator.comparing(Person::getAge))
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);


        //* AllMatch - AnyMatch - NoneMatch
        System.out.println("\n--- ALLMATCH - ANYMATCH - NONEMATCH ---");
        boolean result = people.stream()
                .allMatch(person -> person.getAge() > 6);
        System.out.println(result);

        result = people.stream()
                .anyMatch(person -> person.getAge() > 50);
        System.out.println(result);

        result = people.stream()
                .noneMatch(person -> person.getName().equals("Ahmet"));
        System.out.println(result);


        //* Max - Min
        System.out.println("\n--- MAX-MIN ---");
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);


        //* FindFirst - FindAny
        System.out.println("\n--- FINDFIRST - FINDANY");
        people.stream()
                .findFirst()
                .ifPresent(System.out::println);
        people.stream()
                .findAny()
                .ifPresent(System.out::println);


        //* Reduce
        System.out.println("\n--- REDUCE ---");
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        Integer resultReduce = stream.reduce(1, (i1, i2) -> i1 * i2);
        System.out.println(resultReduce);


        //* Distinct
        System.out.println("\n--- DISTINCT ---");
        Stream<String> stringStream = Stream.of("duck", "duck", "duck", "goose");
        stringStream.distinct().forEach(System.out::println);


        //* Limit - Skip
        System.out.println("\n--- LIMIT - SKIP ---");
        Stream<Integer> integerStream = Stream.iterate(1, i -> i + 1);
        integerStream.skip(5).limit(2).forEach(System.out::println);


        //* Peek
        System.out.println("\n--- PEEK ---");
        long count = people.stream()
                .filter(person -> person.getName().startsWith("F"))
                .peek(System.out::println)
                .count();
        System.out.println(count);


        //! Collectors

        System.out.println("\n--- JOINING ---");
        Stream<String> stream1 = Stream.of("lions", "tigers", "bears");
        String string = stream1.collect(Collectors.joining("/"));
        System.out.println(string);

        System.out.println("\n--- TREESET ---");
        Stream<String> stream2 = Stream.of("lions", "tigers", "bears");
        TreeSet<String> treeSet = stream2.filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);

        System.out.println("\n--- MAP ---");
        Stream<String> stream3 = Stream.of("lions", "tigers", "bears");
        Map<String, String> map = stream3.collect(Collectors.toMap(String::toUpperCase, s -> s));
        System.out.println(map);
        Stream<String> stream4 = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map2 = stream4.collect(Collectors.toMap(
                String::length, k -> k, (v1, v2) -> v1 + "," + v2
        ));
        System.out.println(map2);

        System.out.println("\n--- GROUPINGBY ---");
        Stream<String> stream5 = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> groupMap = stream5.collect(Collectors.groupingBy(String::length));
        System.out.println(groupMap);

        System.out.println("\n--- PARTITIONINGBY ---");
        Stream<String> stream6 = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> partitioningMap = stream6.collect(Collectors.partitioningBy(v -> v.length() <= 5));
        System.out.println(partitioningMap);

        System.out.println("\n--- COUNTING ---");
        Stream<String> stream7 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> countingMap = stream7.collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(countingMap);

        System.out.println("\n--- MAPPING - MINBY ---");
        Stream<String> stream8 = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> collect = stream8.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                (String s) -> s.charAt(0),
                                Collectors.minBy(Comparator.naturalOrder()))
                )
        );
        System.out.println(collect);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Mahmut Can", 20, Gender.MALE, Arrays.asList("Java", "JavaScript", "Go")),
                new Person("Recep Altuntaş", 33, Gender.FEMALE, Arrays.asList("Java", "C#", "C")),
                new Person("Faruk EKrem", 58, Gender.FEMALE, Arrays.asList("C", "Ruby", "Go")),
                new Person("Mehmet Selim", 14, Gender.MALE, Arrays.asList("Python", "JavaScript", "Scala")),
                new Person("Fatih Taş", 99, Gender.MALE, Arrays.asList("Java", "JavaScript", "C++")),
                new Person("Mükremin Güzel", 7, Gender.FEMALE, Arrays.asList("PHP", "JavaScript", "Go")),
                new Person("Necati Ateş", 120, Gender.FEMALE, Arrays.asList("Dart", "JavaScript", "Go")),
                new Person("Kerime Şaşmaz", 120, Gender.FEMALE, Arrays.asList("Kotlin", "JavaScript", "Go"))
                );
    }

}
