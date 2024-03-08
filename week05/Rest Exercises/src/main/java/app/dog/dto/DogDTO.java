package app.dog.dto;

import app.dog.Dog;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DogDTO {

    private Long id;
    private String name;
    private String breed;
    Gender gender;
    private int age;
    Dog dog;

    public DogDTO(Long id, String name, String breed, Gender gender, int age) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
    }

    public DogDTO(String name, String breed, Gender gender, int age) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;

    }
    public DogDTO(String name, String breed, Gender gender, int age, Dog dog) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.dog= dog;
    }

    public enum Gender{
        Male,
        Female
    }
}
