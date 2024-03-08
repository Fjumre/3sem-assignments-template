package app.dog;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.ArrayList;
import java.util.Arrays;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {
    public static void main(String[] args) {

        ArrayList<Dog> dogList= new ArrayList<>(Arrays.asList(
                new Dog(1L, "Basse", "Jens Hansen", 55522233, Dog.SpayedOrNeutered.No),
                new Dog(2L, "Luna", "Emma Green", 12345678, Dog.SpayedOrNeutered.Yes),
                new Dog(3L, "Max", "Oliver Smith", 98765432, Dog.SpayedOrNeutered.No),
                new Dog(4L, "Bella", "Sophia Johnson", 22335566, Dog.SpayedOrNeutered.No),
                new Dog(5L, "Charlie", "James Wilson", 88423325, Dog.SpayedOrNeutered.Yes),
                new Dog(6L, "Molly", "Isabella Martinez", 10104589, Dog.SpayedOrNeutered.Yes)
                    ));


        ObjectMapper om= new ObjectMapper();

        Javalin app = Javalin.create().start(7007);



        app.routes(
                ()->{
                    path("/api/dog", ()-> {
                        get("/", ctx -> ctx.json(dogList));
                        get("{id}", ctx -> {
                            long id = Long.parseLong(ctx.pathParam("id"));

                            Dog d = dogList.stream()
                                    .filter(dog -> dog.getId() == id)
                                    .findFirst()
                                    .orElseThrow(() -> new NotFoundResponse("Dog not found"));
                            ctx.json(d);
                        });
                        post("/", ctx ->{
                            Dog incoming = ctx.bodyAsClass(Dog.class);
                            ctx.json(incoming);
                        });


                        put("{id}", ctx -> {
                            long id = Long.parseLong(ctx.pathParam("id"));

                            Dog dogUpdate = dogList.stream()
                                    .filter(dog -> dog.getId() == id)
                                    .findFirst()
                                    .orElseThrow(() -> new NotFoundResponse("Dog not found"));

                            Dog updatedDog= ctx.bodyAsClass(Dog.class);
                            dogUpdate.setSpayedOrNeutered(updatedDog.getSpayedOrNeutered());


                            ctx.json(updatedDog);
                        });

                        delete("{id}", ctx -> {
                            long id = Long.parseLong(ctx.pathParam("id"));

                            boolean removedDog = dogList.removeIf(dog -> dog.getId() == id);

                            ctx.json(removedDog + " Dog removed");
                        });
                    });
                });
    }
}