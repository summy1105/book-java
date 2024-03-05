package modernaction.chap03_lambda;

import modernaction.chap02.Apple;
import org.junit.jupiter.api.Test;

import java.io.FilenameFilter;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MethodReferenceTest {

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
}