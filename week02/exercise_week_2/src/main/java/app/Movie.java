package app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
public class Movie {
    private String title;
    private LocalDate releaseDate;
    private String overview;
    private double imdbRating;

    public String getReleaseYear() {
        if (releaseDate != null) {
            return DateTimeFormatter.ofPattern("yyyy").format(releaseDate);
        }
        return "Unknown";
    }
}
