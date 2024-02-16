package app.Lombok;

import lombok.*;


@ToString
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode


public class LombokPerson {


    private String firstName;


    private String lastName;

    @EqualsAndHashCode.Exclude
    private int age;

    private Person.Gender gender;


    public enum Gender {
        Male,
        Female
    }


      /*  @Override
    public int hashCode(){
        final Object $name = getFirstName();
        int age = getAge();
        int result= $name+ " "+ age;
        return result;
    }*/
}
