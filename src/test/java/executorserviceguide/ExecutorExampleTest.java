package executorserviceguide;

import org.junit.jupiter.api.Test;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorExampleTest {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    Runnable runnableTask = () -> {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("end runnable task"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    Callable<String> callableTask = () -> {
//        System.out.println("start callable task");
        TimeUnit.MILLISECONDS.sleep(300);
//        System.out.println("end callable task");
        return "Task's execution";
    };

    @Test
    public void generateTest() {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
    }

    @Test
    public void assigningTaskToExecutorService() throws InterruptedException {
        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        // return 값이 없음, task 실행 종료 여부 확인 불가
        executorService.execute(runnableTask);
//        System.out.println("end main thread");

        Future<String> future = executorService.submit(callableTask);
        try {
            long l = System.currentTimeMillis();
            String s = future.get();
            long durationTime = System.currentTimeMillis() - l;
//            System.out.println("durationTime = " + durationTime);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            long l = System.currentTimeMillis();
            // 모든 task들을 각자 시작하지만, 1개의 성공한 task결과를  return한다
            String result = executorService.invokeAny(callableTasks);
            long durationTime2 = System.currentTimeMillis() - l;
            System.out.println("durationTime2 = " + durationTime2);
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 모든 task 결과를 반환
        List<Future<String>> futures = executorService.invokeAll(callableTasks);
    }

    @Test
    public void executorServiceShutdown() {
        // 타스크가 없어도, 자동적으로 destroy 되지 않음,
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 새로운 타스크 할당x, 현재 run중인 thread들이 종료 될때까지 기다리고 destroy
//        executorService.shutdown();

        // 즉시 종료, but 모든 쓰레드가 같은 시간에 종료되는 것은 아님,/ return waiting tasks
//        List<Runnable> notExecutedTasks = executorService.shutdownNow();

        //One good way to shut down the ExecutorService is to use both of these methods combined with the awaitTermination() method
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    @Test
    public void futureInterfaceTest() {
        Future<String> future = executorService.submit(callableTask);
        String result = null;
//        try {
//            long l = System.currentTimeMillis();
//            result = future.get(); // task가 실행 중일 때는 block할 수도 있음,
//            long durationTime = System.currentTimeMillis() - l;
//            System.out.println("durationTime = " + durationTime);
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println("future.isDone() = " + future.isDone());

        try {
            result = future.get(200, TimeUnit.MILLISECONDS); // 200 milli sec 지나면, TimeoutException 발생
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("future.isDone() = " + future.isDone());
            System.out.println("future.cancel(true) = " + future.cancel(true));
            System.out.println("future.isCancelled() = " + future.isCancelled());
        }
    }

    @Test
    public void scheduledExecutorServiceTest() throws InterruptedException {
        ScheduledExecutorService scheduledExeSvc = Executors.newSingleThreadScheduledExecutor();

        // 1초 후에 한번만 실행
        Future<String> resultFuture = scheduledExeSvc.schedule(callableTask, 1, TimeUnit.SECONDS);
        System.out.println("resultFuture.isDone() = " + resultFuture.isDone());
        Thread.sleep(2000);
        System.out.println("resultFuture.isDone() = " + resultFuture.isDone());

        // 0.1 초 후부터 첫번째로 타스크 실행, 0.45초 마다 타스크 실행
        ScheduledFuture<?> scheduledFuture = scheduledExeSvc.scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);
        System.out.println("scheduledFuture.isDone() = " + scheduledFuture.isDone());
        Thread.sleep(10000);
        System.out.println("scheduledFuture.isDone() = " + scheduledFuture.isDone());
        scheduledExeSvc.shutdown();

        scheduledExeSvc = Executors.newSingleThreadScheduledExecutor();
        // 0.1 초 후부터 첫번째로 타스크 실행, 타스크가 끝난후 0.15초 뒤 다음 타스크 실행-반복
        scheduledFuture = scheduledExeSvc.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);
        System.out.println("2scheduledFuture.isDone() = " + scheduledFuture.isDone());
        Thread.sleep(10000);
        System.out.println("2scheduledFuture.isDone() = " + scheduledFuture.isDone());
        scheduledExeSvc.shutdown();
    }
}
