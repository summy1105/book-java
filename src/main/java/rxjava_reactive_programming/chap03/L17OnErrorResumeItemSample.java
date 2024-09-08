package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public class L17OnErrorResumeItemSample {
    public static void main(String[] args) {
        Flowable.just(1, 3, 5, 0, 2, 4)
                .map(data -> 100 / data)
                .onErrorReturnItem(0)
                .subscribe(new DisposableSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("data=" + integer);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error=" + throwable);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");

                    }
                });
    }
}
