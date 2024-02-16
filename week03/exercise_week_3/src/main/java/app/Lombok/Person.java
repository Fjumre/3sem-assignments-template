package app.Lombok;


import lombok.*;


@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode

public class Person {



    private String firstName;


    private String lastName;

    @EqualsAndHashCode.Exclude
    private int age;

    @ToString.Exclude
    private Gender gender;


    public enum Gender{
    Male,
    Female



    }
}