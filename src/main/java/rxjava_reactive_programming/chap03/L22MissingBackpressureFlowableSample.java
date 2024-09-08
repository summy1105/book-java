package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

public class L22MissingBackpressureFlowableSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<Long> flowable = Flowable.interval(10L, TimeUnit.MILLISECONDS)
                .doOnNext(value -> System.out.printf(value+" "))
//                .onBackpressureLatest()
                .onBackpressureBuffer(500) // error = io.reactivex.rxjava3.exceptions.MissingBackpressureException: Buffer is full
                ;

        flowable
//                .observeOn(Schedulers.computation())
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long value) {
                        try {
                            System.out.println("\nwaiting....");
                            Thread.sleep(1000L);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("\nreceived: " + value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.out.println("error = " + error);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("end");
                    }
                });

        Thread.sleep(10000L);
    }
}
