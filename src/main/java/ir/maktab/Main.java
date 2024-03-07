package ir.maktab;


import ir.maktab.mockdata.MockData;
import ir.maktab.model.Person;
import ir.maktab.model.PersonSummary;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = MockData.getPeople();

        List<Person> peopleUnder50 = filterPeopleUnder50(people);
        List<Person> peopleSortedByUsername = sortPeopleByUsername(people);
        List<Person> peopleSortedByAgeAndLastName = sortPeopleByAgeAndLastName(people);
        Set<String> ipv4Set = extractValidIpv4Addresses(people);
        Map<String, PersonSummary> resultMap = createPersonSummaries(people);
        List<PersonSummary> correctedAges = correctAges(people);
        double averageAgeOfMen = calculateAverageAgeOfMen(people);

        System.out.println("Average age of men: " + averageAgeOfMen);
        System.out.println();
        System.out.println("people under 50 : "+peopleUnder50);
        System.out.println();
        System.out.println("people sort by username : "+peopleSortedByUsername);
        System.out.println();
        System.out.println("people Sorted By Age And LastName :"+peopleSortedByAgeAndLastName);
        System.out.println();
        System.out.println(ipv4Set);
        System.out.println();
        System.out.println(resultMap);
        System.out.println();
        System.out.println(correctedAges);

    }

    private static List<Person> filterPeopleUnder50(List<Person> people) {
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
                        .thenComparing(person -> {
                            String[] parts = person.getUsername().split("\\.");
                            return parts.length > 1 ? parts[1] : "";
                        }))
                .collect(Collectors.toList());
    }

    private static Set<String> extractValidIpv4Addresses(List<Person> people) {
        return people.stream()
                .map(Person::getIpv4)
                .filter(ip -> ip.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$"))
                .collect(Collectors.toSet());
    }

    private static Map<String, PersonSummary> createPersonSummaries(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .filter(person -> person.getGender().equalsIgnoreCase("female") && person.getAge() > 40)
                .filter(person -> !person.getFirstName().startsWith("A"))
                .skip(5)
                .limit(100)
                .collect(Collectors.toMap(
                        person -> person.getFirstName() + " " + person.getLastName(),
                        person -> new PersonSummary(person.getId(), person.getFirstName(), person.getLastName(),
                                person.getAge(), person.getBirthDate()),
                        (existing, replacement) -> existing // Merge function to handle duplicate keys
                ));
    }


    private static List<PersonSummary> correctAges(List<Person> people) {
        return people.stream()
                .map(person -> {
                    int age = calculateAge(person.getBirthDate());
                    return new PersonSummary(person.getId(), person.getFirstName(), person.getLastName(), age, person.getBirthDate());
                })
                .collect(Collectors.toList());
    }

    private static double calculateAverageAgeOfMen(List<Person> people) {
        return people.stream()
                .filter(person -> "male".equalsIgnoreCase(person.getGender())) // Filter based on gender field
                .filter(person -> person.getAge() != null) // Filter out null ages
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
    }

    private static int calculateAge(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate today = LocalDate.now();
        LocalDate dob = LocalDate.parse(birthDate, formatter);

        Period period = Period.between(dob, today);
        return period.getYears();
    }
}