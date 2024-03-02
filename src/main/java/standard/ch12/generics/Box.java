package standard.ch12.generics;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Getter
public class Box<T> {
    List<T> list = new ArrayList<>();

    void add(T item){ list.add(item); }
    T get(int i) { return list.get(i); }
    int size(){ return list.size(); }
    public String toString() { return list.toString(); }

    // 지네릭 메소드 확장, Box의 지네릭의 T와 다름 -> 다른 글자로 대체 가능
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        List<T> collect = list.stream().sorted().collect(Collectors.toList());
        System.out.println("collect = " + collect);
        System.out.println("list = " + list);
    }


    static class Fruit implements Eatable, Comparable<Fruit>{
        public String toString() { return "Fruit"; }

        @Override
        public int compareTo(Fruit o) {
            return -1;
        }
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
