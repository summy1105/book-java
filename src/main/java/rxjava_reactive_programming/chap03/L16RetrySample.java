package rxjava_reactive_programming.chap03;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class L16RetrySample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.<Integer>create(emitter -> {
                    System.out.println("Flowable 처리 시작");
                    for (int i = 1; i <= 3; i++) {
                        if (i == 3) {
                            throw new Exception("예외 발생");
                        }
                        // 데이터 통지
                        emitter.onNext(i);
                    }
                    // 완료 통지
                    emitter.onComplete();
                    System.out.println("Flowable 처리 완료");
                }, BackpressureStrategy.BUFFER)
                .doOnSubscribe(subscription -> System.out.println("flowable : doOnSubscribe"))
                .retry(2)
                ;

        flowable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("subscriber: onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Data=" + integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error=" + throwable);
            }

            @Override
            public void onComplete() {
                System.out.println("end");
            }
        });
    }
}
