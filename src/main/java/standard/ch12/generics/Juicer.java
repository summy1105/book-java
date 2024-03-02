package standard.ch12.generics;

import lombok.ToString;

import java.util.List;

import static standard.ch12.generics.Box.*;

public class Juicer {

    static protected Juice makeJuice(Box.FruitBox<? extends Fruit> box) {
        String tmp = "";
        for (Fruit fruit : box.getList()) {
            tmp += fruit +" ";
        }
        return new Juice(tmp);
    }

    // 지네릭 메소드
    static protected <T extends Fruit> Juice makeJuice2(FruitBox<T> box) {
        String tmp = "";
        for (Fruit fruit : box.getList()) {
            tmp += fruit +" ";
        }
        return new Juice(tmp);
    }

    static class Juice {
        String ingredients;

        public Juice(String ingredients) {
            this.ingredients = ingredients;
        }

        public String toString() {
            return ingredients+" juice";
        }
    }
}
