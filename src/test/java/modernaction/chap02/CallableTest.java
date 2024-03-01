package modernaction.chap02;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

public class CallableTest {

    @Test
    public void callableAnonymousClassTest() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                return Thread.currentThread().getName();
            }
        });
        System.out.println(System.currentTimeMillis()-start);

        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
            if (threadName.isDone()) {
                System.out.println("threadName = " + threadName.get());
                break;
            }
        }
        System.out.println(System.currentTimeMillis()-start);
    }

    @Test
    public void callableLambdaTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> threadName = executorService.submit(() -> Thread.currentThread().getName());
        System.out.println("threadName.get() = " + threadName.get());
    }
}
