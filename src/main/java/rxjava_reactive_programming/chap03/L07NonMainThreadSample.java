package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class L07NonMainThreadSample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");

        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .subscribe(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long data) {
                        String threadName = Thread.currentThread().getName(); // RxComputationThreadPool-1
                        System.out.println(threadName + ": " + data);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName+": 완료");
                    }
                });

        System.out.println("end");
        Thread.sleep(1000L);
    }
}