package modernaction.chap04_07_stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomCollectorTest {

    @Test
    public void primeNumbersCollectorTest() {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, 2000).boxed().collect(new PrimeNumbersCollector());
        collect.get(true).forEach(System.out::println);
    }
}
