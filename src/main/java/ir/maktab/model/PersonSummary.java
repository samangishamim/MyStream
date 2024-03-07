package ir.maktab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class PersonSummary {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date birthDate;

    public PersonSummary(Integer id, String firstName, String lastName, Integer age, String birthDate) {
    }
}
