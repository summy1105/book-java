package ch09;

public class MathEx2 {
    public static void main(String[] args) {
        int i = Integer.MIN_VALUE;

        System.out.println(i);
        System.out.println(-i);

        try {
            System.out.println(Math.negateExact(10));
            System.out.println(Math.negateExact(-10));
            System.out.println(Math.negateExact(i));
        }catch (ArithmeticException e){
            System.out.println();
            System.out.println(Math.negateExact((long)i));
        }
    }
}
