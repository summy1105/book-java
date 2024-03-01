package standard.ch12.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static standard.ch12.generics.Box.*;

class GenericsTest {

    @Test
    public void BoxTest() {
        Box<Fruit> fruitBox = new Box<Fruit>();
        Box<Apple> appleBox = new Box<Apple>();
        Box<Toy> toyBox = new Box<Toy>();
//        Box<Grape> grapeBox = new Box<Apple>(); // build error 타입 불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());

        appleBox.add(new Apple());
        appleBox.add(new Apple());
//        appleBox.add(new Toy());

        toyBox.add(new Toy());

        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("toyBox = " + toyBox);
    }

    @Test
    public void FruitBoxTest() {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();
//        FruitBox<Grape> grapeBox = new FruitBox<Apple>(); // build error 타입 불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());

        appleBox.add(new Apple());
//        appleBox.add(new Grape()); //에러 자손 아님

        grapeBox.add(new Grape());


        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("grapeBox = " + grapeBox);
    }
}