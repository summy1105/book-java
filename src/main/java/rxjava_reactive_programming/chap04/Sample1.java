package rxjava_reactive_programming.chap04;

import io.reactivex.rxjava3.core.Flowable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sample1 {
    public static void main(String[] args) throws InterruptedException {
        Flowable<String> flowable = Flowable.fromArray("A", "B", "C", "D", "E");
        flowable.subscribe(new DebugSubscriber<>());

        Flowable.fromIterable(List.of("a", "b", "c", "d", "e")).subscribe(new DebugSubscriber<>());

        Flowable<Long> fromCallable = Flowable.fromCallable(() -> System.currentTimeMillis());
        fromCallable.subscribe(new DebugSubscriber<>("fromCallable 1"));
        fromCallable.subscribe(new DebugSubscriber<>("fromCallable 2"));

        Flowable.range(10, 3).subscribe(new DebugSubscriber<>());

        // interval
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");
        Flowable<Long> interval = Flowable.interval(100L, TimeUnit.MILLISECONDS);
        System.out.println("interval 시작 시각: " + LocalTime.now().format(formatter));
        interval.take(5).subscribe(data -> {
            String threadName = Thread.currentThread().getName();
            String time = LocalTime.now().format(formatter);
            System.out.println(threadName + ": " + time + ": data=" + data);
        });
        Thread.sleep(500L);

        // timer
        System.out.println("timer 시작 시각: " + LocalTime.now().format(formatter));
        Flowable.timer(100L, TimeUnit.MILLISECONDS).subscribe(data -> {
                    String threadName = Thread.currentThread().getName();
                    String time = LocalTime.now().format(formatter);
                    System.out.println(threadName + ": " + time + ": data=" + data);
                }
                , error -> System.out.println("error=" + error)
                , () -> System.out.println("complete")
        );
        Thread.sleep(150L);

        // defer
        System.out.println("defer 시작 시각: " + LocalTime.now().format(formatter));
        Flowable<LocalTime> defer = Flowable.defer(() -> Flowable.just(LocalTime.now()));
        defer.subscribe(new DebugSubscriber<>("No. 1"));
        Thread.sleep(1000L);
        defer.subscribe(new DebugSubscriber<>("No. 2"));
    }
}
