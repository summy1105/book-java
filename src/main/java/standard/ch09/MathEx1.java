package standard.ch09;

import static java.lang.Math.*;

public class MathEx1 {
    public static void main(String[] args) {
        double val = 90.7552;
        System.out.println(round(val));

        val *= 100;
        System.out.println(round(val));

        System.out.println(round(val)/100);
        System.out.println(round(val)/100.0);
        System.out.println();

        System.out.println(ceil(1.1));
        System.out.println(floor(1.5));
        System.out.println(round(1.1));
        System.out.println(round(1.5));//return 이 int
        System.out.println(rint(1.5)); // 반올림 return 이 double
        System.out.println();
        System.out.println(round(-1.5));
        System.out.println(rint(-1.5));// .5일경우 가까운 짝수
        System.out.println(ceil(-1.5));
        System.out.println(floor(-1.5));
    }
}
