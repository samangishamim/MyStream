package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;
import ir.maktab.model.PersonSummary;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> people = MockData.getPeople();

        Main main = new Main();


        // B-1

//        main.filterByAge(people, 50);


        // B-2

//        main.sortByUsername(people);


        // B-3

//        main.sortByAgeAndLastName(people);


        // B-4

        main.getIpv4Set(people);


        // B-5

//        main.getNameToPersonMap(people);


        // B-6

//        main.getCorrectedPeople(people);


        // B-7

//        main.getAverageAgeOfMen(people);

    }


    public void filterByAge(List<Person> people, int age) {

        List<Person> filteredPeople = people.stream()

                .filter(person -> person.getAge() <= age)

                .toList();


        System.out.println(filteredPeople);

    }


    public void sortByUsername(List<Person> people) {

        List<Person> sortedByUsername = people.stream()

                .sorted(Comparator.comparing(Person::getUsername))
                .toList();


//        System.out.println(sortedByUsername);

        for (Person person : sortedByUsername) {
            System.out.println(person.getUsername());
        }
    }


    public void sortByAgeAndLastName(List<Person> people) {

        List<Person> sortedByAgeAndLastName = people.stream()

                .sorted(Comparator.comparing(Person::getLastName))

                .sorted(Comparator.comparing(Person::getAge))

                .toList();


//        System.out.println(sortedByAgeAndLastName);
        for (Person person : sortedByAgeAndLastName) {
            System.out.println(person.getAge()+ " " +person.getLastName() );
        }
    }


    public void getIpv4Set(List<Person> people) {

        Set<String> ipv4Set = people.stream()

//                .map(Person::getIpv4)

                .map(person -> person.getIpv4())
                .collect(Collectors.toSet());


        System.out.println(ipv4Set);

    }


    public void getNameToPersonMap(List<Person> people) {

        Map<String, Person> nameToPerson = people.stream()

                .sorted(Comparator.comparing(Person::getLastName))

                .filter(person -> person.getGender().equals("F") && person.getAge() > 40)

                .skip(5)

                .limit(100)

                .collect(Collectors.toMap(person -> person.getFirstName() + " " + person.getLastName(), person -> person));


        System.out.println(nameToPerson);

    }


    public void getCorrectedPeople(List<Person> people) {

        List<PersonSummary> correctedPeople = people.stream()

                .map(PersonSummary::new)

                .collect(Collectors.toList());


        double averageAge = correctedPeople.stream()

                .filter(person -> person.getGender().equals("M"))

                .mapToInt(PersonSummary::getAge)

                .average()

                .orElse(0);


        System.out.println("Average age of men: " + averageAge);

    }


    public void getAverageAgeOfMen(List<Person> people) {

        List<Person> filteredPeople = people.stream()

                .filter(person -> person.getAge() <= 50)

                .collect(Collectors.toList());


        List<PersonSummary> correctedPeople = filteredPeople.stream()

                .map(PersonSummary::new)

                .collect(Collectors.toList());


        double averageAge = correctedPeople.stream()

                .filter(person -> person.getGender().equals("F"))

                .mapToInt(PersonSummary::getAge)

                .average()

                .orElse(0);


        System.out.println("Average age of female: " + averageAge);

    }
}