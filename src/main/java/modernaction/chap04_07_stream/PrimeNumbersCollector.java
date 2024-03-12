package modernaction.chap04_07_stream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

    public boolean isPrime(List<Integer> primes, int candidate) {
        int candidateRoot = (int)Math.sqrt((double) candidate);
        return primes.stream()
                .takeWhile(i->i <= candidateRoot)
                .noneMatch(i->candidate%i == 0);
    }

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return ()-> new HashMap<>(){{ // 임시함수 생성하는 형태와 비슷, 안의 scope 로 바로 실행됨
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc, candidate)->{
            acc.get( isPrime(acc.get(true), candidate) )
                .add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        // 순차적으로 진행되어야 함, 병렬로 사용할 수 없다. 호출될 일 없으므로 학습목적으로 타자침
        return (map1, map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
}
