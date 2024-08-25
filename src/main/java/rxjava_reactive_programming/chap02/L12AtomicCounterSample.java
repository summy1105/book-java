package rxjava_reactive_programming.chap02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class L12AtomicCounterSample {
    static class AtomicCounter {
        private final AtomicInteger count = new AtomicInteger(0); // 원자성 보장

        void increment() {
            count.incrementAndGet();
        }

        int get() {
            return count.get();
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final AtomicCounter counter = new AtomicCounter();

        Runnable task = () ->{
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Boolean> future1 = executorService.submit(task, true);
        Future<Boolean> future2 = executorService.submit(task, true);

        if (future1.get() && future2.get()) {
            System.out.println(counter.get());
        } else {
            System.out.println("실패");
        }

        executorService.shutdown();
    }
}
