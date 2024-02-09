package app;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExercise4 {


    public static void main(String[] args) {


        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);


        for (int i = 1; i <= 1000000000; i++) {
            final int number = i;
            executorService.submit(() -> {
                if (isPrime(number)) {
                    System.out.println("Time: "+ LocalTime.now() + ", Number: " +number + " is prime.");
                }
            });
        }
        executorService.shutdown();
    }

    public static boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

}
