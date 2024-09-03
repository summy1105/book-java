package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static rxjava_reactive_programming.chap03.L13CounterSample.*;

public class L15CounterWithMergeSample {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        Flowable<Integer> source1 = Flowable.range(1, 10_000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation());

        Flowable<Integer> source2 = Flowable.range(1, 10_000)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation());

        Flowable.merge(source1, source2)
                .subscribe(data -> counter.increment()
                        , error -> System.out.println("에러=" + error)
                        , () -> System.out.println("counter.get()=" + counter.get())
                );

        Thread.sleep(1_000L);
    }
}
