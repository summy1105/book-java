package standard.ch09;

public class StringEx6 {
    public static void main(String[] args) {
        int iVal = 100;
        String strVal = String.valueOf(iVal);

        double dVal = 200.0;
        String strVal2 = dVal + "";

        double sum = Integer.parseInt("+"+strVal) + Double.valueOf(strVal2);

        double sum2 = Integer.valueOf(strVal.trim()) + Double.valueOf(strVal2.trim());

        System.out.println(String.join("",strVal, "+", strVal2,"=")+sum);
        System.out.println(strVal+"+"+strVal2+"="+sum2);
    }
}
