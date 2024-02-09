package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;

public class MovieDatabaseClient {

    private static final String API_KEY = "6f5c58cb3b005353793fb52d88308498";
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .create();
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public Movie fetchMovieDetailsByImdbId(String imdbId) {

        String url = "https://api.themoviedb.org/3/find/" + imdbId
                + "?api_key=" + API_KEY
                + "&external_source=imdb_id";

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();


            MoviesResponse moviesResponse = gson.fromJson(responseBody, MoviesResponse.class);
            if (moviesResponse != null && !moviesResponse.getMovieResults().isEmpty()) {
                return moviesResponse.getMovieResults().get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        MovieDatabaseClient client = new MovieDatabaseClient();

        Movie movie = client.fetchMovieDetailsByImdbId("tt0111161");
        if (movie != null) {
            System.out.println("Overview: " + movie.getOverview()+"\nRelease year: "+movie.getReleaseYear());
        } else {
            System.out.println("Movie not found.");
        }
    }

}

