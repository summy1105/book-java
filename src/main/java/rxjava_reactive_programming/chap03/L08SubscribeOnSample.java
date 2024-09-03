package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class L08SubscribeOnSample {
    // 유효한 스케줄러 예제
    // 첫번째로 설정된 스케줄러만 유효하고 그 뒤에 설정한 subscribeOn 스케줄러는 무시됨
    public static void main(String[] args) throws InterruptedException {
        Flowable.just(1, 2, 3, 4, 5)
                .subscribeOn(Schedulers.computation()) //RxComputationThreadPool-1
                .subscribeOn(Schedulers.io()) //RxCachedThreadScheduler-1 무시됨
                .subscribeOn(Schedulers.single()) //RxSingleScheduler-1 무시됨
                .subscribe(data -> {
                    Thread.sleep(1000L);
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + ": " + data);
                });

        Thread.sleep(10000);
    }
}
