package app.veterinarian;

import lombok.*;

import java.time.LocalDateTime;


    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public class Appointment {
        private int id;
        private LocalDateTime date;
        private String name;
    }


