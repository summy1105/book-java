package modernaction.chap04_07_stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReduceTest {
    List<Dish> menu;

    @BeforeEach
    public void fillMenuList() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french", false, 530, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.MEAT),
                new Dish("season fruit", true, 120, Dish.Type.MEAT),
                new Dish("pizza", true, 550, Dish.Type.MEAT),
                new Dish("prawns", false, 300, Dish.Type.MEAT),
                new Dish("salmon", false, 450, Dish.Type.MEAT)
        );
    }

    @Test
    public void sumTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum = " + sum);

        sum = integers.stream().reduce(0, Integer::sum);
        System.out.println("sum = " + sum);
    }

    @Test
    public void minMaxTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> reduce = integers.stream().reduce((a, b) -> a > b? b : a); // 첫번째 요소가 바로 a로
        reduce.ifPresent(System.out::println);
        integers.stream().reduce(Integer::min);

        integers.stream().reduce(Integer::max).ifPresent(System.out::println);

        integers = Arrays.asList(1);
        Optional<Integer> nullReduce = integers.stream().reduce((a, b) -> a+b);
        nullReduce.ifPresent(System.out::println);
    }

    @Test
    public void quiz5_3() {
        Integer menuCount = menu.stream().map(d -> 1).reduce(0, Integer::sum);
        Assertions.assertEquals(menu.size(), menuCount);
        long count = menu.stream().count();
    }

    @Test
    public void numberStreamTest() {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("calories = " + calories);
    }

    @Test
    public void returnIntStreamToIntegerStream() {
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();
        stream.forEach(System.out::println);

        Stream<Double> doubleStream = menu.stream().mapToInt(Dish::getCalories).asDoubleStream().boxed();
        doubleStream.forEach(System.out::println);
    }

    @Test
    public void optionalIntTest() {
        OptionalInt max = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        max.ifPresent(System.out::println);
        System.out.println("max.orElse(1) = " + max.orElse(1));

        max = OptionalInt.empty();
        System.out.println("max.orElse(1) = " + max.orElse(0));
    }

    @Test
    public void rangeClosedTest() {
        long count = IntStream.range(1, 100).count();
        System.out.println("count = " + count);
        OptionalInt min = IntStream.range(1, 100).min();
        System.out.println("min = " + min.orElse(0));
        OptionalInt max = IntStream.range(1, 100).max();
        System.out.println("max = " + max.orElse(1000));


        long count2 = IntStream.rangeClosed(1, 100).count();
        System.out.println("count2 = " + count2);
        OptionalInt min2 = IntStream.rangeClosed(1, 100).min();
        System.out.println("min2 = " + min2.orElse(0));
        OptionalInt max2 = IntStream.rangeClosed(1, 100).max();
        System.out.println("max2 = " + max2.orElse(1000));
    }
}
