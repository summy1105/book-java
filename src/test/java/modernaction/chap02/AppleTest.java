package modernaction.chap02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppleTest {

    List<Apple> inventory;

    final int COLOR_CNT = 2;
    final int BASIC_WEIGHT = 100;

    @BeforeEach
    public void fillInventory() {
        inventory = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Apple.Color color = Apple.Color.getByNumber(i % COLOR_CNT);
            inventory.add(new Apple(color, i+BASIC_WEIGHT));
        }
    }

    @Test
    void filterGreenApplesTest() {
        List<Apple> apples = Apple.filterGreenApples(inventory);
        assertEquals(50, apples.size());
    }

    @Test
    void filterApplesByColorTest() {
        List<Apple> apples = Apple.filterApplesByColor(inventory, Apple.Color.GREEN);
        assertEquals(50, apples.size());
        apples = Apple.filterApplesByColor(inventory, Apple.Color.RED);
        assertEquals(50, apples.size());
    }


    @Test
    void filterApplesByPredicateTest() {
        List<Apple> greenApples = Apple.filterApples(inventory, new Apple.AppleGreenColorPredicate());
        List<Apple> heavyApples = Apple.filterApples(inventory, new Apple.AppleHeavyWeightPredicate());
        List<Apple> redNHeavyApples = Apple.filterApples(inventory, new Apple.AppleRedAndHeavyPredicate());
        assertEquals(50, greenApples.size());
        assertEquals(49, heavyApples.size());
        assertEquals(24, redNHeavyApples.size());
    }

    @Test
    void prettyPrintAppleTest() {
        Apple.prettyPrintApple(inventory, apple -> {
            boolean isHeavy = apple.getWeight() > 150;
            return String.format("A %s %s apple.", isHeavy?"heavy":"light", apple.getColor().name().toLowerCase());
        });

        Apple.prettyPrintApple(inventory, apple -> String.format("An apple of %dg", apple.getWeight()));
    }

    @Test
    void sortApplesAndPrint() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple apple1, Apple apple2) {
                return apple1.getColor().value-apple2.getColor().value;
            }
        });
        Apple.prettyPrintApple(inventory,apple -> {
            boolean isHeavy = apple.getWeight() > 150;
            return String.format("A %s %s apple.", isHeavy?"heavy":"light", apple.getColor().name().toLowerCase());
        });

        inventory.sort((apple1, apple2)-> apple2.getWeight().compareTo(apple1.getWeight()));
        Apple.prettyPrintApple(inventory,apple -> String.format("An apple of %dg", apple.getWeight()));
    }
}