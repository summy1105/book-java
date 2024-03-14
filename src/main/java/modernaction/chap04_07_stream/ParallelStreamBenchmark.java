package modernaction.chap04_07_stream;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {
    /*
    command : java -jar ./target/benchmarks.jar modernaction.chap04_07_stream.ParallelStreamBenchmark
     */


    private static final long N = 10_000_000;

    @Benchmark
    public long sequentialSum() {
        return Stream.iterate(1L, i->i+1)
                .limit(N)
                .reduce(0L, Long::sum);
    }
    /*
    Benchmark                              Mode  Cnt   Score   Error  Units
    ParallelStreamBenchmark.sequentialSum  avgt   10  51.586 ± 1.079  ms/op
     */


    @Benchmark
    public long parallelSum() {
        return Stream.iterate(1L, i->i+1) // iterate를 청크로 분할하기 어려움
                .limit(N)
                .parallel()
                .reduce(0L, Long::sum);
    }
    /*
    Benchmark                            Mode  Cnt   Score   Error  Units
    ParallelStreamBenchmark.parallelSum  avgt   10  53.580 ± 0.714  ms/op
     */


    @Benchmark
    public long parallelLongStreamSum() {
        return LongStream.rangeClosed(1, N)
                .parallel()
                .reduce(0L, Long::sum);
    }
    /*
    Benchmark                                      Mode  Cnt  Score   Error  Units
    ParallelStreamBenchmark.parallelLongStreamSum  avgt   10  0.586 ± 0.016  ms/op
     */

    @Benchmark
    public long iterativeSUm() {
        long result = 0;
        for (long i = 1L; i <N ; i++) {
            result += i;
        }
        return result;
    }
    /*
    Benchmark                              Mode  Cnt   Score   Error  Units
    ParallelStreamBenchmark.iterativeSUm   avgt   10   3.154 ± 0.053  ms/op
     */

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }
}
