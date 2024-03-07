package modernaction.chap04_07_stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

}