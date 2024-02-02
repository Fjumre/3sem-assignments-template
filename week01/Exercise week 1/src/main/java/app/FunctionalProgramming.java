package app;

import java.util.Arrays;

public class FunctionalProgramming {

    public interface MyTransformingType {
        int transform(int number);
    }

    public interface MyValidatingType {
        boolean validate(int number);
    }

    public static void main(String[] args) {
        MyTransformingType doubler = number -> number * 2;
        MyTransformingType incrementer = number -> number + 1;

        System.out.println("Double of 5: " + doubler.transform(5));
        System.out.println("Increment 5: " + incrementer.transform(5));





        MyValidatingType divisible = number -> number % 3 == 0;
        MyValidatingType positive = number -> number > 0;

        System.out.println("Is it divisible by 3? " + divisible.validate(9));
        System.out.println("Is -5 positive/over 0? " + positive.validate(-5));





        int[] myArray = {1, 2, 3, 4, 5};


        int[] squaredNumbers = mapExample(myArray, (x) -> x * x);
        int[] evenNumbers = filterExample(myArray, (x) -> x % 2 == 0);
        int[] twice = mapExample(myArray, doubler);


        System.out.println("Squared Numbers: " + Arrays.toString(squaredNumbers));
        System.out.println("Even Numbers: " + Arrays.toString(evenNumbers));
        System.out.println("Double Numbers: " + Arrays.toString(twice));
    }

    public static int[] mapExample(int[] a, MyTransformingType op) {

        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = op.transform(a[i]);
        }
        return result;
    }

    public static int[] filterExample(int[] a, MyValidatingType op) {
        return Arrays.stream(a)
                .filter(op::validate)
                .toArray();
    }
}

