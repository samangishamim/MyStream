package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;
import ir.maktab.model.PersonSummary;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    private static List<Person> filterPeopleByAge(List<Person> people) {
        return people.stream()
                .filter(person -> person.getAge() <= 50)
                .collect(Collectors.toList());
    }

    private static List<Person> sortPeopleByUsername(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getUsername))
                .collect(Collectors.toList());
    }

    private static List<Person> sortPeopleByAgeAndLastName(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(person -> person.getUsername().split("\\.")[1]))
                .collect(Collectors.toList());
    }

    private static Set<String> extractIpv4FromPeople(List<Person> people) {
        return people.stream()
                .map(Person::getIpv4)
                .collect(Collectors.toSet());
    }
}