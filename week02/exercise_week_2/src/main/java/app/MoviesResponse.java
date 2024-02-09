package app;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

public class MoviesResponse {
    @Getter
    @Setter

    private List<Movie> movieResults = new ArrayList<>();
    public List<Movie> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<Movie> movieResults) {
        this.movieResults = movieResults;
    }
}