package standard.ch13thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinEx1 {
    static public final ForkJoinPool POOL = new ForkJoinPool();

    static public class SumTask extends RecursiveTask<Long> {
        long from;
        long to;

        public SumTask(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            long size = to - from +1;
            if (size <= 5) {
                return sum();
            }
            long half = (from + to) / 2;

            SumTask leftSum = new SumTask(from, half);
            SumTask rightSum = new SumTask(half + 1, to);

            leftSum.fork();

            return rightSum.compute() + leftSum.join();
        }

        private long sum() {
            long tmp = 0L;
            for (long i = from; i <= to; i++) {
                tmp += i;
            }
            return tmp;
        }
    }
}
