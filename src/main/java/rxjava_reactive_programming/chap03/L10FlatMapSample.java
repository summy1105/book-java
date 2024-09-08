package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class L10FlatMapSample {
    public static void main(String[] args) throws InterruptedException {

        Flowable<String> flowable = Flowable.just("A", "B", "C")
//                .delay(1000L, TimeUnit.MILLISECONDS)
                .flatMap(data -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("flatMap "+threadName + " :" + data);
                    return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS); //RxComputationThreadPool
//                    return Flowable.just(data).subscribeOn(Schedulers.computation()); //RxComputationThreadPool
//                    return Flowable.just(data).subscribeOn(Schedulers.computation())
//                            .doOnNext(next->{
//                                Thread.sleep(1000L);
//                                String threadName = Thread.currentThread().getName();
//                                System.out.println(threadName + " flat :" + next);
//                            });
                })
                ;

        System.out.println(Thread.currentThread().getName());

        flowable
                .observeOn(Schedulers.newThread(), false, 3)
                .subscribe(data ->{
                    Thread.sleep(1000L);
                    String threadName = Thread.currentThread().getName();
                    System.out.println("sub1 "+threadName + " :" + data);
                });

        flowable
                .observeOn(Schedulers.single(), false, 3)
                .subscribe(data -> {
                    Thread.sleep(1000L);
                    String threadName = Thread.currentThread().getName();
                    System.out.println("sub2 "+threadName + " :" + data);
                });
        Thread.sleep(8000L);
    }

    /**
     * 출력
     * main
     * flatMap main :A
     * flatMap main :B
     * flatMap main :C
     * flatMap main :A
     * flatMap main :B
     * flatMap main :C
     * (1s)
     * sub1 RxNewThreadScheduler-1 :A
     * sub2 RxSingleScheduler-1 :C
     * (1s)
     * sub1 RxNewThreadScheduler-1 :C
     * sub2 RxSingleScheduler-1 :A
     * (1s)
     * sub1 RxNewThreadScheduler-1 :B
     * sub2 RxSingleScheduler-1 :B
     */
}
