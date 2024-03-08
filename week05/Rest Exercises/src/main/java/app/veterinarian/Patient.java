package app.veterinarian;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Patient {
    private int id;
    private String name;
    private List<String> allergies;
    private List<String> medications;
    private List<String> surgeries;

}