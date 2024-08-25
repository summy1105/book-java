package rxjava_reactive_programming.chap02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SynchronizedPoint {
    private final Object lock = new Object(); // rightUp 메서드가 실행되는 동아, x혹은 y값을 다른 스레드가 가져가는 것을 방지

    private int x = 0;
    private int y = 0;

    void rightUp() {
        synchronized (lock) {
            x++;
            y++;
        }
    }

    int getX() {
        synchronized (lock) {
            return x;
        }
    }
    int getY() {
        synchronized (lock) {
            return y;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final SynchronizedPoint point = new SynchronizedPoint();

        Runnable task = () ->{
            for (int i = 0; i < 10_000; i++) {
                point.rightUp();
                if (i % 1_000 == 0) {
                    String thName = Thread.currentThread().getName();
                    System.out.println(thName+": "+ point.getX() +", "+ point.getY());
                }
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Boolean> future1 = executorService.submit(task, true);
        Future<Boolean> future2 = executorService.submit(task, true);

        if (future1.get() && future2.get()) {
            System.out.println(point.getX());
            System.out.println(point.getY());
        } else {
            System.out.println("실패");
        }

        executorService.shutdown();
    }
}
