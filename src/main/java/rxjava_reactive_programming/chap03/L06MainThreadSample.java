package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

public class L06MainThreadSample {
    //메인 스레드에서 처리 작업을 하는 Flowable 예제
    public static void main(String[] args) {
        System.out.println("start");

        Flowable.just(1, 2, 3)
                .subscribe(new ResourceSubscriber<Integer>() {
                    @Override
                    public void onNext(Integer data) {
                        String threadName = Thread.currentThread().getName(); // main thread
                        System.out.println(threadName + ": " + data);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        String threadName = Thread.currentThread().getName(); // main thread
                        System.out.println(threadName+": 완료");
                    }
                });
        System.out.println("end");
    }
}
