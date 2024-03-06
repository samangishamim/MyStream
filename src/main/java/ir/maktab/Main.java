package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;
import ir.maktab.model.PersonSummary;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    private static List<Person> filterPeopleByAge(List<Person> people) {
        return people.stream()
                .filter(person -> person.getAge() <= 50)
                .collect(Collectors.toList());
    }


}