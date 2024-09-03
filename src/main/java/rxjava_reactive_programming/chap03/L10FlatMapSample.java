package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class L10FlatMapSample {
    public static void main(String[] args) throws InterruptedException {

        Flowable<String> flowable = Flowable.just("A", "B", "C")
//                .delay(1000L, TimeUnit.MILLISECONDS)
                .flatMap(data -> {

//                    return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS); //RxComputationThreadPool
//                    return Flowable.just(data).subscribeOn(Schedulers.computation()); //RxComputationThreadPool
                    return Flowable.just(data).subscribeOn(Schedulers.computation())
                            .doOnNext(next->{
                                Thread.sleep(1000L);
                                String threadName = Thread.currentThread().getName();
                                System.out.println(threadName + " flat :" + next);
                            });
                })
                ;

        System.out.println(Thread.currentThread().getName());

        flowable
//                .observeOn(Schedulers.computation(), false, 3)
                .subscribe(data ->{
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " :" + data);
                });


        Thread.sleep(4000L);
    }
}
