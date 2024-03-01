package standard.ch12.generics;

import java.util.ArrayList;
import java.util.List;

public class Box<T> {
    List<T> list = new ArrayList<>();

    void add(T item){ list.add(item); }
    T get(int i) { return list.get(i); }
    int size(){ return list.size(); }
    public String toString() { return list.toString(); }


    static class Fruit implements Eatable {
        public String toString() { return "Fruit"; }
    }
    static class Apple extends Fruit {
        public String toString() { return "Apple"; }
    }
    static class Grape extends Fruit {
        public String toString() { return "Grape"; }
    }
    static class Toy {
        public String toString() { return "Toy"; }
    }

    // "&"기호로 여러개 extends 가능
    static class FruitBox<T extends Fruit & Eatable> extends Box<T>{}
}
