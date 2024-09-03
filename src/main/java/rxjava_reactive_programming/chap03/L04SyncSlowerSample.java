package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;

import java.util.concurrent.TimeUnit;

public class L04SyncSlowerSample {
    // 데이터를 받는 측이 무거운 처리 작업을 하는 예제
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> System.out.println("emit: " + System.currentTimeMillis() + "ms - "
                        + data+"\t"+Thread.currentThread().getName()))//RxComputationThreadPool-1
                .subscribe(data -> {
                    System.out.println("onNext data: "+ data+"\t"+ Thread.currentThread().getName()); //RxComputationThreadPool-1
                    Thread.sleep(2000L);
                });

        Thread.sleep(5000L);
    }
}
