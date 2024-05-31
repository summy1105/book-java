package modernaction.chap20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RecurrenceStreamTest {

    @Test
    void primesTest() {
        Assertions.assertThrows(Exception.class, ()->{
            IntStream numbers = RecurrenceStream.numbers();

            IntStream filtered = RecurrenceStream.primes(numbers);
            filtered.forEach(System.out::println);
        });
    }
}