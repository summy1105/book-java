package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;

import java.util.concurrent.TimeUnit;

public class L05SyncFasterSample {
    //Interval 메서드로 생성한 Flowable 예제
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("emit: " + System.currentTimeMillis() + "ms -" + data))//RxComputationThreadPool-1
                .subscribe(data -> Thread.sleep(500L));//RxComputationThreadPool-1

        Thread.sleep(3000L);
    }
}
