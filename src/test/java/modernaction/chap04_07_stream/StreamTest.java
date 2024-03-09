package modernaction.chap04_07_stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    @DisplayName("피타고라스 수")
    public void pythagoreanTriples() {
        Stream<double[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                ).filter(t -> t[2] % 1 == 0);
        stream.forEach(t -> System.out.println(t[0] + " " + t[1] + " " + t[2]));
    }

    @Test
    public void makeStream() {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void nullAndNullableStream() {
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);

        homeValueStream = Stream.ofNullable(System.getProperty("home"));

        // config, home, user system properties 값들의 stream 가져오기
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);
    }

    @Test
    public void intArrayToStream() {
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);
    }

    @Test
    public void fileStreamTest() {
        long uniqueWords = 0;
        String path = Paths.get("").toAbsolutePath() + "/src/test/java/modernaction/chap04_07_stream/DishStreamTest.java";
        try (Stream<String> lines = Files.lines(Paths.get(path), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split("[, ]")))
                    .distinct()
                    .count();
            System.out.println("uniqueWords = " + uniqueWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void iterateTest() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("피보나치수열 집합")
    public void quiz5_4() {
        Stream.iterate(new Integer[]{0, 1}, n->new Integer[]{n[1], n[0]+n[1]})
                .limit(20)
                .forEach(t-> System.out.printf("(%d, %d)\n", t[0], t[1]));

        Stream.iterate(new Integer[]{0, 1}, n->new Integer[]{n[1], n[0]+n[1]})
                .limit(20)
                .map(t->t[0])
                .forEach(System.out::println);
    }

    @Test
    public void iteratePredicate() {
        IntStream.iterate(0, n->n<100, n->n+4 )
                .forEach(System.out::println);
        IntStream.iterate(0, n->n+4)
                .takeWhile(n->n<100)
                .forEach(System.out::println);
    }

    @Test
    public void generateTest() {
        IntStream ones = IntStream.generate(()->1); // immutable

        // generate는 순서를 보장하지 않음
        IntStream.generate(new IntSupplier() { //mutable
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        }).limit(10).forEach(System.out::println);
    }
}
