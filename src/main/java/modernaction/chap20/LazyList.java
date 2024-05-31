package modernaction.chap20;

import java.awt.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;
    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    public boolean isEmpty() {
        return false;
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        Predicate<Integer> isPrime = n -> n % numbers.head() != 0;

        return new LazyList<>(numbers.head()
                , () -> {
                    MyList<Integer> param = numbers.tail().filter(isPrime);
                    return primes( param );
                }
        );
    }
    // LazyList(2, ()-> from(3))
    // => LazyList(2, ()-> {primes((3, ()->from(4)).filter(2배수X?))} )
    // => LazyList(2, ()-> primes( LazyList( 3, LazyList(4, ()->from(5)).filter(2배수X?) ) ) )
    //
    // LazyList(4, ()->from(5)).filter(2배수X?)
    // => LazyList(5, ()->from(6)).filter(2배수X?)
}
