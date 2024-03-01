package standard.ch09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class ObjectsTest {
    public static void main(String[] args) {
        String[][] str2D = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};
        String[][] str2D_2 = new String[][]{{"aaa","bbb"},{"AAA","BBB"}};

        System.out.println("str2D = {");
        for (String[] tmp : str2D){
            System.out.println(Arrays.toString(tmp));
        }
        System.out.println("}\n");

        System.out.println("str2D_2 = {");
        for (String[] tmp : str2D_2){
            System.out.println(Arrays.toString(tmp));
        }
        System.out.println("}\n");

        System.out.println(Objects.equals(str2D, str2D_2));
        System.out.println(Objects.deepEquals(str2D, str2D_2));
        System.out.println(Objects.isNull(null));
        System.out.println(Objects.nonNull(null));
        System.out.println(Objects.hashCode(null));
        System.out.println(Objects.toString(null));
        System.out.println(Objects.toString(null, ""));
        System.out.println();

        Comparator c = String.CASE_INSENSITIVE_ORDER; // 대소문자 구분 안하는 comparator
        System.out.println(Objects.compare("aa", "bb", c));
        System.out.println(Objects.compare("bb", "aa", c));
        System.out.println(Objects.compare("ab", "AB", c));
    }
}
