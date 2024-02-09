package app;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSON {

    public static Person[] readJsonFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Person[] people = objectMapper.readValue(new File(filePath), Person[].class);
            return people;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String jsonFilePath = "account.json";
        Person[] people = readJsonFile(jsonFilePath);

        if (people != null) {
            for (Person person : people) {
                System.out.println(person);
            }
        }
    }
}
