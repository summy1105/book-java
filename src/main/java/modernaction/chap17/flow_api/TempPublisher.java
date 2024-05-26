package modernaction.chap17.flow_api;

import java.util.concurrent.Flow;

public class TempPublisher {

    public static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return (Flow.Subscriber<? super TempInfo> subscriber) -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

    public static Flow.Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return (Flow.Subscriber<? super TempInfo> subscriber) -> {
            TempProcessor tempProcessor = new TempProcessor();
            tempProcessor.subscribe(subscriber);
            tempProcessor.onSubscribe(new TempSubscription(tempProcessor, town));
        };
    }
}
