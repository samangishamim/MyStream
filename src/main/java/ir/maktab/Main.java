package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;
import ir.maktab.model.PersonSummary;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

//        main.getIpv4Set(people);


        // B-5

//        main.getNameToPersonMap(people);


        // B-6

        main.getCorrectedPeople(people);


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
            System.out.println(person.getAge() + " " + person.getLastName());
        }
    }


    public void getIpv4Set(List<Person> people) {

        Set<String> ipv4Set = people.stream()

                .map(Person::getIpv4)

                .collect(Collectors.toSet());

//        System.out.println(ipv4Set);
        for (String s : ipv4Set) {
            System.out.println(s);
        }

    }


    public void getNameToPersonMap(List<Person> people) {

        Map<String, Person> nameToPerson = people.stream()

                .sorted(Comparator.comparing(Person::getLastName))

                .filter(person -> (person.getGender().equals("Female") && person.getAge() <= 40) ||
                        person.getGender().equals("Male"))

                .sorted(Comparator.comparing(Person::getFirstName))

                .dropWhile(person -> person.getFirstName().startsWith("A"))

                .skip(5)

                .limit(100)

                .collect(Collectors.toMap(person -> person.getId() + person.getFirstName() +
                        person.getLastName(), person -> person));


//        System.out.println(nameToPerson);

        System.out.println(nameToPerson.size());
        System.out.println(nameToPerson.keySet());
    }


    public void getCorrectedPeople(List<Person> people) {

        List<PersonSummary> correctedPeople = people.stream()

                .map(person -> new PersonSummary(person.getId(),
                        person.getFirstName(), person.getLastName(),
                        calAge(person.getBirthDate()), person.getGender(),
                        convertDate(person.getBirthDate())))

                .toList();

        OptionalDouble maleAvgAge
                = correctedPeople.stream()
                .filter(personSummary -> personSummary.getGender().equals("Male"))
                .map(PersonSummary::getAge)
                .mapToInt(Integer::intValue)
                .average();

        for (PersonSummary correctedPerson : correctedPeople) {
            System.out.println(correctedPerson);
        }
        System.out.println(maleAvgAge);

    }

    private static int calAge(String birthDate) {
        String modifyDate = correctDate(birthDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withLocale(Locale.US);
        LocalDate date = LocalDate.parse(modifyDate, formatter);
        LocalDate dateNow = LocalDate.now();
        return Period.between(date, dateNow).getYears();
    }

    private static String correctDate(String birthDate) {
        String d = "";
        String m = "";
        String y = "";
        String modifyDate = "";
        if (birthDate.length() < 10) {
            String[] split = birthDate.split("/");
            y = split[2];
            if (split[0].length() < 2)
                d += "0" + split[0];
            else
                d = split[0];
            if (split[1].length() < 2)
                m += "0" + split[1];
            else
                m = split[1];
            modifyDate = d + "/" + m + "/" + y;
        } else
            modifyDate = birthDate;
        return modifyDate;
    }

    private static Date convertDate(String birthDate) {
        String modifyDate = correctDate(birthDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formatter = formatter.withLocale(Locale.US);
        LocalDate date = LocalDate.parse(modifyDate, formatter);
        Date date1 = java.sql.Date.valueOf(date);
        return date1;
    }

//    public void getAverageAgeOfMen(List<Person> people) {
//
//        List<Person> filteredPeople = people.stream()
//
//                .filter(person -> person.getAge() <= 50)
//
//                .toList();
//
//
//        List<PersonSummary> correctedPeople = filteredPeople.stream()
//
//                .map(PersonSummary::new)
//
//                .toList();
//
//
//        double averageAge = correctedPeople.stream()
//
//                .filter(person -> person.getGender().equals("F"))
//
//                .mapToInt(PersonSummary::getAge)
//
//                .average()
//
//                .orElse(0);
//
//
//        System.out.println("Average age of female: " + averageAge);
//
//    }
}