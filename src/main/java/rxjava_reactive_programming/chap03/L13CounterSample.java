package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class L13CounterSample {
    public static void main(String[] args) throws InterruptedException {
        final Counter counter = new Counter();

        Flowable.range(1, 10_000)
                .subscribeOn(Schedulers.computation()) //Flowable 을 다른 쓰레드에서 처리하게 한다.
                .observeOn(Schedulers.computation()) // 다른 쓰레드에서 처리 작업을 하게 한다.
                .subscribe(
                        data -> counter.increment() // 데이터를 받을때의 처리
                        , error -> System.out.println("error =" + error)
                        , () -> System.out.println("counter.get()=" + counter.get())
                );

        Flowable.range(1, 10_000)
                .subscribeOn(Schedulers.computation()) //Flowable 을 다른 쓰레드에서 처리하게 한다.
                .observeOn(Schedulers.computation()) // 다른 쓰레드에서 처리 작업을 하게 한다.
                .subscribe(
                        data -> counter.increment() // 데이터를 받을때의 처리
                        , error -> System.out.println("error =" + error)
                        , () -> System.out.println("counter.get()=" + counter.get())
                );

        Thread.sleep(1000L);
    }

    public static class Counter {
        private volatile int count;

        void increment() {
            count++;
        }

        int get() {
            return count;
        }
    }
}
