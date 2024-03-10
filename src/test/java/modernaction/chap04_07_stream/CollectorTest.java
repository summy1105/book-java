package modernaction.chap04_07_stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorTest {
    List<Dish> menu;

    @BeforeEach
    public void fillMenuList() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", false, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }

    @Test
    public void reducingTest1() {
        Long collect = menu.stream().collect(counting());
        Assertions.assertEquals(menu.size(), collect);
        
        Comparator<Dish> dishCaloriesComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> max = menu.stream().collect(maxBy(dishCaloriesComparator));
        Optional<Dish> min = menu.stream().collect(minBy(dishCaloriesComparator));
        System.out.println("max.get() = " + max.get());
        System.out.println("min.get() = " + min.get());

        Integer totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("totalCalories = " + totalCalories);

        Double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("averageCalories = " + averageCalories);

        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

//        menu.stream().collect(Collectors.joining()) // 안됨
        String joiningMenu = menu.stream().map(Dish::toString).collect(joining(", "));
        System.out.println("joiningMenu = " + joiningMenu);
    }

    @Test
    public void reducingTest2() {
        Integer sum = menu.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a + b));
        Assertions.assertEquals(4200, sum);

        Optional<Dish> maxCaloriesDish = menu.stream().collect(reducing((a, b) -> a.getCalories() > b.getCalories() ? a : b));
        Assertions.assertEquals("pork", maxCaloriesDish.get().getName());
    }

    @Test
    @DisplayName("reduce를 누적으로 사용하는 방법으로 의미론적으로 잘못 사용되는 방법이다.")
    public void integerStreamToListByReducing() {
        List integerList = Arrays.asList(1, 2, 3, 4, 5, 6).stream()
                .reduce(new ArrayList<Integer>()
                        , (List list, Integer number) -> {
                            list.add(number);
                            return list;
                        }
                        , (List list1, List list2) -> {
                            list1.addAll(list2);
                            return list1;
                        });
        System.out.println("integerList = " + integerList);
    }

    @Test
    public void quiz6_1() {
        String s = menu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get();
        System.out.println("s = " + s);
    }

    enum CaloricLevel { DIET, NORMAL, FAT};

    @Test
    public void groupingTest() {
        Map<Dish.Type, List<Dish>> groupByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("groupByType = " + groupByType);

        groupByType = menu.stream().collect(groupingBy(Dish::getType, () -> new HashMap<>(), toList()));
        System.out.println("groupByType = " + groupByType);


        Map<CaloricLevel, List<Dish>> groupByCalories = menu.stream().collect(groupingBy(d -> {
            if (d.getCalories() <= 400) return CaloricLevel.DIET;
            else if (d.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
        System.out.println("groupByCalories = " + groupByCalories);

        Map<Dish.Type, List<Dish>> groupByTypeAndFilteredCalories = menu.stream().collect(
                groupingBy(Dish::getType
                        , filtering(d -> d.getCalories() > 500
                                , toList())
                )
        );
        System.out.println("groupByTypeAndFilteredCalories = " + groupByTypeAndFilteredCalories);
    }

    @Test
    public void groupingTest2() {
        Map<Dish.Type, List<String>> dishNamesByType = menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("dishNamesByType = " + dishNamesByType);
    }

    @Test
    public void groupingTest3() {
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", Arrays.asList("greasy","salty"));
        dishTags.put("beef", Arrays.asList("salty","roasted"));
        dishTags.put("chicken", Arrays.asList("fried","crisp"));
        dishTags.put("french fries", Arrays.asList("greasy","fried"));
        dishTags.put("rice", Arrays.asList("light","natural"));
        dishTags.put("season fruit", Arrays.asList("fresh","natural"));
        dishTags.put("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put("salmon", Arrays.asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> dishNamesByType = menu.stream().collect(
                groupingBy(Dish::getType
                , flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
                )
        );
        System.out.println("dishNamesByType = " + dishNamesByType);
    }

    @Test
    public void groupingTest4() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType
                        , groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                )
        );
        System.out.println("dishesByTypeCaloricLevel = " + dishesByTypeCaloricLevel);
    }

    @Test
    public void groupingTest5() {
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println("typesCount = " + typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("mostCaloricByType = " + mostCaloricByType);
    }
}
