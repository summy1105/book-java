package modernaction.chap04_07_stream;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

public class ForkJoinSumCalculatorTest {

    @Test
    public void forkJoinSumTest() {
        long rangeEnd = 10_000_000L;
        long[] numbers = LongStream.rangeClosed(1, rangeEnd).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        Long result = new ForkJoinPool().invoke(task);
        System.out.println("result = " + result);
    }
}