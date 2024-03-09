package ir.maktab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
public class PersonSummary {


    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String birthDate;

    public PersonSummary(Person person) {

        this.firstName = person.getFirstName();

        this.lastName = person.getLastName();

        this.age = person.getAge();

        this.gender= person.getGender();

        this.birthDate =person.getBirthDate();

    }
}
