package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ThreadExercise6 {


    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String[] urls = new String[]{
            "https://icanhazdadjoke.com",
            "https://api.chucknorris.io/jokes/random",
            "https://api.kanye.rest",
            "https://api.whatdoestrumpthink.com/api/v1/quotes/random",
            "https://pokeapi.co/api/v2/pokemon/ditto",
            "https://api.agify.io?name=michael",
            "https://dog.ceo/api/breeds/image/random",
            "https://restcountries.com/v3.1/name/India?fullText=true",
            "https://api.publicapis.org/entries",
            "https://api.coindesk.com/v1/bpi/currentprice.json"
    };

    public static void main(String[] args) {
        new ThreadExercise6().fetch();
    }

    private void fetch() {
        for (String url : urls) {
            getResponseBody2(url);
        }
    }

    private void getResponseBody2(String url) {

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                String json = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);


                if (url.contains("icanhazdadjoke")) {
                    Joke joke = gson.fromJson(json, Joke.class);
                    System.out.println(joke.getJoke());
                } else if (url.contains("chucknorris")) {
                    Value value = gson.fromJson(json, Value.class);
                    System.out.println(value.getValue());
                } else if (url.contains("kanye.rest")) {
                    Quote quote = gson.fromJson(json, Quote.class);
                    System.out.println(quote.getQuote());
                } else if (url.contains("whatdoestrumpthink")) {
                    Message message = gson.fromJson(json, Message.class);
                    System.out.println(message.getMessage());
                } else if (url.contains("pokeapi")) {
                    Name name = gson.fromJson(json, Name.class);
                    System.out.println(name.getName());
                } else if (url.contains("agify")) {
                    Name name = gson.fromJson(json, Name.class);
                    Age age = gson.fromJson(json, Age.class);
                    System.out.println(name.getName() + " " + age.getAge());
                } else if (url.contains("dog")) {
                    Message message = gson.fromJson(json, Message.class);
                    System.out.println(message.getMessage());
                } else if (url.contains("restcountries")) {
                    Country[] country = gson.fromJson(json, Country[].class);
                    System.out.println(country[0].getName().getOfficial());
                }else if (url.contains("publicapis")) {
                   Count count = gson.fromJson(json, Count.class);
                    System.out.println(count.getCount());
                }else if (url.contains("coindesk")) {
                    Bitcoin bitcoin = gson.fromJson(json, Bitcoin.class);
                    System.out.println(bitcoin.getBpi().getEUR().getRate_float());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    @ToString
    public class Joke {
        String joke;
    }

    @Getter
    @Setter
    @ToString
    public class Value {
        String value;
    }

    @Getter
    @Setter
    @ToString
    public class Quote {
        String quote;
    }

    @Getter
    @Setter
    @ToString
    public class Message {
        String message;
    }

    @Getter
    @Setter
    @ToString
    public class Name {
        String name;
    }

    @Getter
    @Setter
    @ToString
    public class Age {
        String age;
    }
    @Getter
    @Setter
    @ToString
    public class Country {
        Name name;

        @Getter
        @Setter
        @ToString
        public static class Name {
            String common;
            String official;
        }
    }
    @Getter
    @Setter
    @ToString
    public class Count{
        int count;
    }
    @Getter
    @Setter
    @ToString
    public class Bitcoin {
        Bpi bpi;

        @Getter
        @Setter
        @ToString
        public static class Bpi {
            Currency EUR;

            @Getter
            @Setter
            @ToString
            public static class Currency {
                double rate_float;
            }
        }
    }

}