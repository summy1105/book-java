package modernaction.chap20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkedListTest {
    @Test
    public void myLinkedListTest() {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
        System.out.println("linkedList.head() = " + linkedList.head());
        System.out.println("linkedList.tail().head() = " + linkedList.tail().head());
        Assertions.assertThrows(UnsupportedOperationException.class
                , () -> System.out.println("linkedList.tail().tail().head() = " + linkedList.tail().tail().head()));
    }

    @Test
    public void lazyListTeat() {
        LazyList<Integer> numbers = LazyList.from(2);
        Integer two = numbers.head;
        Integer three = numbers.tail().head();
        Integer four = numbers.tail().tail().head();
        System.out.println("two = " + two);
        System.out.println("three = " + three);
        System.out.println("four = " + four);
    }

    @Test
    public void lazyListTest2() {
        LazyList<Integer> numbers = LazyList.from(2);
        Integer two = LazyList.primes(numbers).head();
        Integer three = LazyList.primes(numbers).tail().head();
        Integer five = LazyList.primes(numbers).tail().tail().head();
        System.out.println(two + " " + three + " " + five);
    }

    @Test
    public void lazyListTest3() {
        MyList<Integer> numbers = LazyList.primes(LazyList.from(2));

        for (int i = 0; i < 5; i++) {
            System.out.printf(numbers.head()+ ", ");
            numbers = numbers.tail();
        }
    }
}