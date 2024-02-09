package app;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExercise1 {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);


        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String toPrint = "" + letter + letter + letter;
            executorService.submit(() -> System.out.println(toPrint));
        }


        executorService.shutdown();
    }
}

