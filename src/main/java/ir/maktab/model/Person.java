package ir.maktab.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String favoriteColor;
    private String birthDate;
    private String ipv4;
    private String ipv6;

    public Person(Integer id, String firstName, String lastName, Integer age, String gender, String email, String phone
            , String username, String favoriteColor, String birthDate, String ipv4, String ipv6) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.favoriteColor = favoriteColor;
        this.birthDate = birthDate;
        this.ipv4 = ipv4;
        this.ipv6 = ipv6;
    }

}
