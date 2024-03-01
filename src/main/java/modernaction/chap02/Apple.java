package modernaction.chap02;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static modernaction.chap02.Apple.Color.*;

@Data
public class Apple {
    enum Color {
        RED(0), GREEN(1);
        final int value;
        Color(int value){
            this.value=value;
        }
        static Color getByNumber(int value){
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

