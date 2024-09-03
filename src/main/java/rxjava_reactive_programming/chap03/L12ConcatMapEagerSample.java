package rxjava_reactive_programming.chap03;

import io.reactivex.rxjava3.core.Flowable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class L12ConcatMapEagerSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.just("A", "B", "C")
                .concatMapEager(data -> Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS));

        flowable.subscribe(data ->{
            String threadName = Thread.currentThread().getName();
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss.SSS"));

            System.out.println(threadName + ": data=" + data + ", time=" + time);
        });

        Thread.sleep(2000L);
    }
}
