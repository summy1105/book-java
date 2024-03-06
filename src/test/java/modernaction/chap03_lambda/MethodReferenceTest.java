package modernaction.chap03_lambda;

import modernaction.chap02.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class MethodReferenceTest {

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
    public void expression1() {
        Function<Apple, Integer> function = (Apple apple) -> apple.getWeight();
        function = Apple::getWeight;
    }

    @Test
    public void expression2() {
        Runnable runnable = () -> Thread.currentThread().dumpStack();
//        runnable = Thread.currentThread()::dumpStack; //error
        runnable = Thread::dumpStack;
    }

    @FunctionalInterface
    static interface Cut <T, V> {
        public T doSubstring(T str, V value);
    }

    @Test
    public void expression3() {
         Cut<String, Integer> method = (str, i) -> str.substring(i);
         method = String::substring;
    }

    private boolean isValidName(String s) {
        return Character.isUpperCase(s.charAt(0));
    }

    @Test
    public void expression4() {
        Predicate<String> doValid = (String s) -> this.isValidName(s);
        doValid = this::isValidName;
    }

    @Test
    public void useMethodReference() {
        List<String> strList = Arrays.asList("Ask", "Eds", "cds", "es");
        strList.stream().filter(this::isValidName).forEach(System.out::println);
    }

    public List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(function.apply(i));
        }
        return result;
    }

    @Test
    public void mapMethodTest() {
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> appleList = map(weights, Apple::new);
        appleList.forEach(System.out::println);
    }

    static class TripleFieldClass {
        int a;
        int b;
        int c;

        public TripleFieldClass(int a, int b, int c) {
            this.a=a;
            this.b=b;
            this.c=c;
        }
    }

    @FunctionalInterface
    static interface TripleParameterFunction<T>{
        T makeSomeClass(int i, int j, int k);
    }

    @Test
    public void tripleClassMethodReference() {
        TripleParameterFunction<TripleFieldClass> function = (int i, int j, int k) -> new TripleFieldClass(i, j, k);
        function = TripleFieldClass::new;
    }

    @Test
    public void compareMethodReference() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        inventory.sort((a1, a2)->a1.getWeight().compareTo(a2.getWeight()));

        inventory.sort(Comparator.comparing(a->a.getWeight()));

        inventory.sort(Comparator.comparing(Apple::getWeight));
        inventory.forEach(System.out::println);
    }

    @Test
    public void comparatorTest() {
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
//        inventory.forEach(System.out::println);

        inventory.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight));
        inventory.forEach(System.out::println);
    }

    @Test
    public void predicateCombinationTest() {
        Predicate<Apple> redApple = (Apple a) -> a.getColor().equals(Apple.Color.GREEN);
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(a -> a.getWeight() > 150).or(a -> Apple.Color.GREEN.equals(a.getColor()));
    }

    @Test
    public void functionCombinationTest() {
        Function<Integer,Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g); // g(f(x))
        Function<Integer, Integer> i = f.compose(g); // f(g(x))
    }

    @Test
    public void transformationPipelineTest() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline =
                addHeader.andThen(Letter::checkSpelling)
                        .andThen(Letter::addFooter);
        String lambda = transformationPipeline.apply("labda");
        System.out.println(lambda);
    }
}
