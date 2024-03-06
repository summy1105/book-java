package modernaction.chap02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static modernaction.chap02.Apple.Color.*;

@Getter
@Setter
public class Apple {
    public enum Color {
        RED(0), GREEN(1);
        final int value;
        Color(int value){
            this.value=value;
        }
        public static Color getByNumber(int value){
            switch (value){
                case 0 : return RED;
                case 1: return GREEN;
                default:
                    throw new IllegalStateException("Unexpected value: " + value);
            }
        }
    }

    final Color color;
    final Integer weight;

    public Apple(Integer weight) {
        this(GREEN, weight);
    }

    public Apple(Color color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    public String toString() {
        return String.format("a %s Apple : %dg", this.color.name().toLowerCase(), this.weight);
    }

    public static class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public static class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return GREEN.equals(apple.getColor());
        }
    }

    public static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return RED.equals(apple.getColor())
                    && apple.getWeight()> 150;
        }
    }


    public static void prettyPrintApple(List<Apple> inventory, Function<Apple, String> makeMessage) {
        for (Apple apple : inventory) {
            String output = makeMessage.apply(apple);
            System.out.println(output);
        }
    }
}

