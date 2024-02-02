package app;

import java.lang.reflect.Array;

public class Main {

    interface ArithmeticOperation {
        int perform(int a, int b);
    }


    public static void main(String[] args) {

        ArithmeticOperation ad1 = (int a, int b) -> a+ b;
        ArithmeticOperation sub1 = (int a, int b) -> a- b;
        ArithmeticOperation mul1 = (int a, int b) -> a * b;
        ArithmeticOperation div1 = (int a, int b) -> a / b;
        ArithmeticOperation mol1 = (int a, int b) -> a % b;


        System.out.println("result " + ex1(ad1,12,6));
        System.out.println("result " + ex1(sub1,12,6));
        System.out.println("result " + ex1(mul1,12,6));
        System.out.println("result " + ex1(div1,12,6));
        System.out.println("result " + mol1.perform(12,6));
        System.out.println("result2 " + ex1(mol1,12,6));


        int[] arrayA ={3,6,7};
        int [] arrayB = {5,9,6};
        int[] result = ex2(arrayA, arrayB, ad1);

        for (int value: result){
            System.out.println("resultat " + value);
        }


    }

    public static int ex1(ArithmeticOperation operation, int a, int b){

        return operation.perform(a, b);

    }

        public static int[] ex2(int[] a, int[] b, ArithmeticOperation op) {

        if (a.length!= b.length){
            System.out.println("error");
        }


        int[] result = new int [a.length];
                for(int i =0; i <a.length; i++){
                    result[i]=op.perform(a[i], b[i]);

            }
            return result;
        }
    }
