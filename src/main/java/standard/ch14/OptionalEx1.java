package standard.ch14;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalEx1 {
    public static void main(String[] args) {
        Optional<String> optStr = Optional.of("abcde");
        Optional<Integer> optInt = optStr.map(String::length);
//        System.out.println("optStr="+optStr.get());
//        System.out.println("optInt="+optInt.get());

        int result1 = Optional.of("123").filter(x->x.length()>0)
                    .map(Integer::parseInt).get();

        int result2 = Optional.of("")
                    .filter(x->x.length()>0)
                    .map(x->Integer.parseInt(x)).orElse(-1);

//        System.out.println("result1="+result1);
//        System.out.println(result2);

//        Optional.of("456").map(Integer::parseInt)
//                    .ifPresent(System.out::println);

        ///////////////////////////////////////////////////////////////
        OptionalInt optInt1 = OptionalInt.of(0);
        OptionalInt optInt2 = OptionalInt.empty();

//        System.out.println(optInt1.isPresent());
//        System.out.println(optInt2.isPresent());
//        System.out.println();
//
//        System.out.println(optInt1.getAsInt());
////        System.out.println(optInt2.getAsInt()); // NoSuchElementException
//        System.out.println(optInt1);
//        System.out.println(optInt2);
//        System.out.println(optInt1.equals(optInt2));

        ////////////////////////////////////////////////////////////////
        Optional<String> opt1 = Optional.ofNullable(null);
        Optional<String> opt2 = Optional.empty();
//        System.out.println(opt1);
//        System.out.println(opt2);
//        System.out.println(opt1.equals(opt2));

        ////////////////////////////////////////////////////////////////
        int result3 = optStrToInt(Optional.of("123"), 0);
        int result4 = optStrToInt(Optional.of(""), 0);

        System.out.println(result3);
        System.out.println(result4);
    }

    static int optStrToInt(Optional<String> optStr, int defaultValue) {
        try{
            return optStr.map(Integer::parseInt).get();
        }catch (Exception e){
            return defaultValue;
        }
    }
}
