package app.dog.controller;

import app.dog.dto.DogDTO;

import java.util.*;

public class DogHandler {
    private final Map<Long, DogDTO> dogs = new HashMap<>();

    public DogHandler() {

                dogs.put(10L, new DogDTO(10L, "Basse", "Labrador", DogDTO.Gender.Male, 5));
                dogs.put(11L, new DogDTO(11L,"Luna", "Golden Retriever", DogDTO.Gender.Female, 3));
                dogs.put(12L, new DogDTO(12L, "Max", "Beagle", DogDTO.Gender.Male, 4));
                dogs.put(13L, new DogDTO(13L, "Bella", "French Bulldog", DogDTO.Gender.Female, 2));
                dogs.put(14L, new DogDTO(14L, "Charlie", "Border Collie", DogDTO.Gender.Male, 6));
                dogs.put(15L, new DogDTO(15L, "Molly", "Pug", DogDTO.Gender.Female, 7));
    }
    public Map<Long, DogDTO> getAllDogs(){
        return dogs;

    }

    public DogDTO getDogById(Long id){
        return dogs.get(id);
    }
    public void createDog(DogDTO dogDTO){

        dogs.containsKey(dogDTO.getId());

        DogDTO newDogDTO= new DogDTO(dogDTO.getId(),
                dogDTO.getName(),
                dogDTO.getBreed(),
                dogDTO.getGender(),
                dogDTO.getAge());

        dogs.put(dogDTO.getId(), newDogDTO);

    }

    public void updateDog(Long id, DogDTO dogDTO){

        dogs.put(id, dogDTO);

    }

    public void deleteDog(Long id){

        dogs.remove(id);
    }


}
