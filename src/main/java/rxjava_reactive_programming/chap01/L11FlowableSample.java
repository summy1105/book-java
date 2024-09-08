package rxjava_reactive_programming.chap01;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class L11FlowableSample {
    static class L11Subscriber implements Subscriber<String> {
        private Subscription subscription;
        @Override
        public void onSubscribe(Subscription subscription) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Subscriber onSubscribe "+threadName);
            this.subscription = subscription;
            this.subscription.request(1L);
        }

        @Override
        public void onNext(String data) {
            String threadName = Thread.currentThread().getName();
            System.out.println("Subscriber onNext "+threadName + ":" + data);
            this.subscription.request(1L);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            String threadName = Thread.currentThread().getName();
            System.out.println("Subscriber onComplete "+threadName + ": 완료");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {
                String threadName = Thread.currentThread().getName();
                System.out.println("publisher "+threadName);
                String[] datas = {"Hello, World!", "안녕, RxJava"};
                for (String data : datas) {
                    emitter.onNext(data);// data가 널이면 안됨
                }
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.single())
                ;

        flowable
                .observeOn(Schedulers.computation()) //
                .subscribe(new L11Subscriber())
        ;

        Thread.sleep(500L);
    }
    /**
     * 출력
     * Subscriber onSubscribe main
     * publisher RxSingleScheduler-1
     * Subscriber onNext RxComputationThreadPool-1:Hello, World!
     * Subscriber onNext RxComputationThreadPool-1:안녕, RxJava
     * Subscriber onComplete RxComputationThreadPool-1: 완료
     */
}
