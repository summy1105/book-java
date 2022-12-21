package ch09;
import static java.lang.Math.*;

public class MathEx3 {
    public static void main(String[] args) {
        int x1=1, y1=1;
        int x2=2, y2=2;

        double c = sqrt(pow(x2-x1, 2) + pow(y2-y1, 2)); // pow(a,b) a의 n제곱
        double a = c * sin(PI/4);
        double b = c * cos(PI/4);
//        double b = c * cos(toRadians(45));

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(atan2(a,b));
        System.out.println(atan2(a,b)*180/PI); //44.99999999999999
        System.out.println(toDegrees(atan2(a,b))); //atan2는 직각삼각형에서 두변의 길이를 알면 끼인각을 return
        System.out.println(24*log10(2));
        System.out.println(53*log10(2));
    }
}
