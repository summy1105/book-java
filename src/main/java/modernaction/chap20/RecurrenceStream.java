package modernaction.chap20;

import java.util.stream.IntStream;
import java.util.stream.Stream;

// 에러 발생
public class RecurrenceStream {
    public static IntStream numbers() {
        return IntStream.iterate(2, n -> n + 1);
    }

    public static int head(IntStream numbers) {
        return numbers.findFirst().getAsInt();
    }

    static IntStream tail(IntStream numbers) {
        return numbers.skip(1);
    }

    public static IntStream primes(IntStream numbers) {
        int head = head(numbers); // stream 소비됨
        return IntStream.concat(IntStream.of(head)
                , primes(tail(numbers).filter(n -> n % head != 0))); // 무한 재귀 위험
    }
}
