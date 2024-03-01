package standard.ch09;

import java.util.Random;

public class RandomEx1 {
    public static void main(String[] args) {
        Random rand = new Random(1);
        Random rand2 = new Random(1);

        for (int i = 0; i < 5; i++) {
            System.out.println(rand.nextInt());
        }

        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.println(rand2.nextInt());
        }
    }
}
