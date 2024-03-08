package app.dog;

import java.util.HashMap;
import java.util.Map;

public class DogMap {
    private Map<Long, Dog> dogMap;
    public DogMap() {
        dogMap = new HashMap<>();
        Map<Long, Dog> dogMap = new HashMap<>();
        dogMap.put(1L, new Dog(1L, "Basse", "Jens Hansen", 55522233, Dog.SpayedOrNeutered.No));
        dogMap.put(2L, new Dog(2L, "Luna", "Emma Green", 12345678, Dog.SpayedOrNeutered.Yes));
        dogMap.put(3L, new Dog(3L, "Max", "Oliver Smith", 98765432, Dog.SpayedOrNeutered.No));
        dogMap.put(4L, new Dog(4L, "Bella", "Sophia Johnson", 22335566, Dog.SpayedOrNeutered.No));
        dogMap.put(5L, new Dog(5L, "Charlie", "James Wilson", 88423325, Dog.SpayedOrNeutered.Yes));
        dogMap.put(6L, new Dog(6L, "Molly", "Isabella Martinez", 10104589, Dog.SpayedOrNeutered.Yes));
    }
}
