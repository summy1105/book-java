package standard.ch12.generics;

import org.junit.jupiter.api.Test;

import java.util.Optional;

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
    
    @Test
    public void JuicerTest() {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        
        appleBox.add(new Apple());
        appleBox.add(new Apple());


        grapeBox.add(new Grape());

        System.out.println("Juicer.makeJuice(fruitBox) = " + Juicer.makeJuice(fruitBox));
        System.out.println("Juicer.makeJuice(appleBox) = " + Juicer.makeJuice(appleBox));
        System.out.println("Juicer.makeJuice(grapeBox) = " + Juicer.makeJuice(grapeBox));
    }

    @Test
    public void JuicerGenericMethodCallTest() {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        
        grapeBox.add(new Grape());

        // 컴파일러가 generic 타입 추정가능 -> 생략가능
        System.out.println("Juicer.<Fruit>makeJuice2(fruitBox) = " + Juicer.<Fruit>makeJuice2(fruitBox));
        System.out.println("Juicer.<Apple>makeJuice2(appleBox) = " + Juicer.<Apple>makeJuice2(appleBox));
        System.out.println("Juicer.<Grape>makeJuice2(grapeBox) = " + Juicer.<Grape>makeJuice2(grapeBox));
    }

    @Test
    public void genericSortMethodTest() {

        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple());
        fruitBox.add(new Grape());

        appleBox.add(new Apple());
        appleBox.add(new Apple());

        grapeBox.add(new Grape());

        Box.sort(fruitBox.list);
        Box.sort(appleBox.list);
        Box.sort(grapeBox.list);
    }

    @Test
    public void genericsTypeCasting() {
        Box box = null;
        Box<Object> objBox = null;
        box = (Box) objBox; // 경고
        objBox = (Box<Object>) box; // 경고

//        objBox = (Box<Object>) new Box<String>(); // 에러
//        objBox = new Box<String>(); // 에러

        Box<? extends Object> objBox2 = new Box<String>(); // 가능

        FruitBox<? extends Fruit> fruitBox = new FruitBox<Fruit>(); //가능
        fruitBox = new FruitBox<Apple>(); //가능
        fruitBox = new FruitBox<Grape>(); //가능


//        Optional<?> EMPTY = new Optional<?>(); // 에러, Optional construct private && 타입 미확인 생성 X
        Optional<?> EMPTY = Optional.of(null); // Object

        Optional<?> wildOpt = Optional.of(null);
        Optional<Object> objectOpt = Optional.of(null);

        Optional<String> stringOpt = (Optional<String>) wildOpt; // 형변환 가능
//        stringOpt = (Optional<String>) objectOpt; // 형변환 불가
    }
}