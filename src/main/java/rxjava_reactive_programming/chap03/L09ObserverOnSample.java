package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

import java.util.concurrent.TimeUnit;

public class L09ObserverOnSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable
                = Flowable.interval(300L, TimeUnit.MILLISECONDS)// 300밀리초마다 0부터 시작하는 데이터를 통지
                .onBackpressureDrop();// BackpressureMode.DROP

        flowable.observeOn(Schedulers.computation(), false, 2) // drop일때, buffer size => request(bufferSize)
                .subscribe(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long item) {
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            System.exit(1);
                        }

                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + ": " + item);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        Thread.sleep(7000L);
    }
}
