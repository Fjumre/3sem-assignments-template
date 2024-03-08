package app.veterinarian.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AppointmentDTO {
    private int id;
    private LocalDateTime date;

}
