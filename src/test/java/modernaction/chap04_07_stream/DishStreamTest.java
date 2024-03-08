package modernaction.chap04_07_stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DishStreamTest {

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
//            new Dish("prawns", false, 300, Dish.Type.MEAT),
            new Dish("salmon", false, 450, Dish.Type.MEAT)
        );
    }

    @Test
    public void printStreamExecuteProcess() {
        List<String> names = menu.stream().filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                }).map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                }).limit(3).distinct()
                .collect(Collectors.toList());
        System.out.println("names = " + names);
    }

    @Test
    public void filterDistinct() { // 중복 제거
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i->i%2==0).distinct().forEach(System.out::println);
    }

    @Test
    public void filterDistinct2() { // 객체 중복 제거X 당연
        menu.stream().filter(d-> !d.isVegetarian()).distinct().forEach(System.out::println);
    }

    @Test
    public void takeWhileTest() {
        menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories))
                .takeWhile(d -> d.getCalories() < 500)
                .forEach(System.out::println);
    }

    @Test
    public void dropWhileTest() {
        menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories))
                .dropWhile(d->d.getCalories()<500)
                .forEach(System.out::println);
    }

    @Test
    public void skipTest() {
        menu.stream()
                .filter(d -> d.getCalories() > 500)
                .skip(1)
                .forEach(System.out::println);
    }

    @Test
    public void mapTest() {
        List<Integer> collect = menu.stream()
                .map(d -> d.getName().length())
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    List<String> words = Arrays.asList("Modern", "Java", "In", "Action");

    @Test
    public void mapNArrayStreamTest() {
        List<Stream<String>> collect = words.stream()
                .map(w -> w.split(""))
                .map(strArr -> Arrays.stream(strArr))
                .collect(Collectors.toList());
    }

    @Test
    public void flatMapTest() {
        List<String> collect = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    @Test
    public void quiz5_2_1() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = integers.stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    @Test
    public void quiz5_2_2() {
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4);
        List<String> collect = integers1.stream()
                .flatMap(i1 -> integers2.stream().map(i2 -> "[" + i1 + ", " + i2 + "]"))
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }

    @Test
    public void quiz5_2_3() {
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4);
        List<String> collect = integers1.stream()
                .flatMap(i1 -> integers2.stream().filter(i2->(i1+i2)%3==0).map(i2 -> "[" + i1 + ", " + i2 + "]"))
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
    }
}
