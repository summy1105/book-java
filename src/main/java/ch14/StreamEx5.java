package ch14;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx5 {
    public static void main(String[] args) {
        String[] strArr = {
                "Inheritance", "Java", "Lamb", "stream"
                , "OptionalDouble", "Instream", "count", "sum"
        };

        Stream.of(strArr).forEach(System.out::println);
        System.out.println();
        ///////////////////////////////////////////////////////////////////////////////
        boolean noEmptyStr = Stream.of(strArr).noneMatch(s->s.length()==0);
        Optional<String> sWord = Stream.of(strArr).filter(s->s.charAt(0)=='s').findFirst();

        System.out.println(noEmptyStr);
        System.out.println(sWord.get());
        System.out.println();
        //////////////////////////////////////////////////////////////////////////////

        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);

        int count = intStream1.reduce(0, (a,b)-> a+1);
        int sum = intStream2.reduce(0, (a,b)->a+b);

        OptionalInt max = intStream3.reduce(Integer::max);
        OptionalInt min = intStream4.reduce(Integer::min);

        System.out.println(count);
        System.out.println(sum);
        System.out.println(max.getAsInt());
        System.out.println(min.getAsInt());
    }
}
