import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Streams {

    public static void main(String[] args) {

        //! Stream

        List<Person> people = getPeople();

        //* Map
        /*people.stream()
                .map(person -> person.getName())
                .mapToInt(String::length)
                .forEach(System.out::println);*/

        //* Filter
        /*List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());
        females.forEach(System.out::println);*/

        //* Sort
        /*people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(System.out::println);*/

        //* AllMatch
        /*boolean result = people.stream()
                .allMatch(person -> person.getAge() > 6);
        System.out.println(result);*/

        //* AnyMatch
        /*boolean result = people.stream()
                .anyMatch(person -> person.getAge() > 50);
        System.out.println(result);*/

        //* NoneMatch
        /*boolean result = people.stream()
                .noneMatch(person -> person.getName().equals("Ahmet"));
        System.out.println(result);*/

        //* Max - Min
        /*people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);*/

        //* Group
        /*BiConsumer<Gender, List<Person>> genderListBiConsumer = (gender, personList) -> {
            System.out.println(gender);
            personList.forEach(System.out::println);
        };
        people.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .forEach(genderListBiConsumer);*/


    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Mahmut Can", 20, Gender.MALE),
                new Person("Recep Altuntaş", 33, Gender.FEMALE),
                new Person("Faruk EKrem", 58, Gender.FEMALE),
                new Person("Mhmet Selim", 14, Gender.MALE),
                new Person("Fatih Taş", 99, Gender.MALE),
                new Person("Mükremin Güzel", 7, Gender.FEMALE),
                new Person("Necati Ateş", 120, Gender.FEMALE),
                new Person("Kerime Şaşmaz", 120, Gender.FEMALE)
                );
    }

}
