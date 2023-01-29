import src.Education;
import src.Person;
import src.Sex;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long underage = persons.stream()
                .filter(s -> s.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних: " + underage);

        List<String> recruit = persons.stream()
                .filter(s -> s.getAge() >= 18 && s.getAge() < 27 && s.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Подлежащие призыву: " + recruit);

        List<String> worker = persons.stream()
                .filter(s -> s.getAge() >= 18 && s.getAge() <= 65 && (s.getEducation() == Education.HIGHER || s.getEducation() == Education.FURTHER))
                .sorted(Comparator.comparing(s -> s.getFamily()))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Потенциальные работники: " + worker);
    }
}
