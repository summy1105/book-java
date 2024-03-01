package standard.ch14;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorEx1 {
    public static void main(String[] args) {
        String[] strArr = {"aaa", "bbb", "ccc"};
        String result = Stream.of(strArr).collect(new ConcatCollector());

        System.out.println(Arrays.toString(strArr));
        System.out.println(result);
    }
}

class ConcatCollector implements Collector<String, StringBuilder, String>{

    @Override
    public Supplier<StringBuilder> supplier() {
        return ()->new StringBuilder();
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb, s)-> sb.append(s);
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (s1, s2)->s1.append(s2);
    }

    @Override
    public Function<StringBuilder, String> finisher() {
        return sb->sb.toString();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
