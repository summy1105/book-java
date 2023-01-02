package ch09;

import java.util.Random;

public class RandomEx2 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] number = new int[100];
        int[] counter = new int[10];

        for (int i = 0; i < number.length; i++) {
//            System.out.print(number[i] = (int)(Math.random()*10));
//            9<=x<=10 범위 정수
            System.out.print(number[i] = rand.nextInt(10));
        }
        System.out.println();

        for (int i = 0; i < number.length; i++) {
            counter[number[i]]++;
        }

        for (int i = 0; i < counter.length; i++) {
            System.out.println(printGraph('#', counter[i]) +" "+ counter[i]);
        }

    }

    public static String printGraph(char ch, int values){
        char[] bar = new char[values];

        for (int i = 0; i < bar.length; i++) {
            bar[i] = ch;
        }
        return new String(bar);
    }
}
