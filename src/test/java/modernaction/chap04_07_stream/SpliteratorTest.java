package modernaction.chap04_07_stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorTest {

    final String SENTENCE =
            "Nel     mezzo del cammin di nostra vita " +
                    "mi ritrovai in una   selva oscura " +
                    "ch   la dritta via era  smarrita ";

    public int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    @Test
    public void countWordsIterativelyTest() {
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");
    }

    @Test
    public void wordCounterTest() {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

        WordCounter wordCounter = stream.reduce(new WordCounter(0, true)
                , WordCounter::accumulate
                , WordCounter::combine);
        System.out.println("wordCounter.getCounter() = " + wordCounter.getCounter());
    }

    @Test
    @DisplayName("WordCounter 병렬로 수행하기 - 실패")
    public void parallelWordCountTest() {
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wordCounter = stream.parallel().reduce(new WordCounter(0, true)
                , WordCounter::accumulate
                , WordCounter::combine);

        System.out.println("wordCounter.getCounter() = " + wordCounter.getCounter());
        //원래 문자열을 임의의 위치에서 나눔 -> 하나의 단어를 둘로 계산
    }

    @Test
    public void spliteratorWordCountTest() {
        WordCounterSpliterator spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        WordCounter wordCounter = stream.parallel().reduce(new WordCounter(0, true)
                , WordCounter::accumulate
                , WordCounter::combine);
        System.out.println("wordCounter.getCounter() = " + wordCounter.getCounter());
    }
}
