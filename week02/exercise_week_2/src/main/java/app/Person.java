package app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class Person {

    private String firstName;
    private String lastName;
    private String birthDate;
    private Address address;
    private Account account;
}
