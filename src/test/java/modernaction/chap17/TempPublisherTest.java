package modernaction.chap17;

import io.reactivex.Observable;
import modernaction.chap17.flow_api.TempInfo;
import modernaction.chap17.flow_api.TempPublisher;
import modernaction.chap17.flow_api.TempSubscriber;
import modernaction.chap17.use_rxjava.TemperatureObserver;
import modernaction.chap17.use_rxjava.TemperatureObservable;
import org.junit.jupiter.api.Test;

class TempPublisherTest {

    @Test
    public void getTemperatureTest() {
        TempPublisher.getTemperatures("New York").subscribe(new TempSubscriber());
    }

    @Test
    public void getCelsiusTemperaturesTest() {
        TempPublisher.getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
    }

    @Test
    public void getTemperatureTest_RxJava() {
        Observable<TempInfo> observable = TemperatureObservable.getTemperature("New York");
        observable.blockingSubscribe(new TemperatureObserver());
    }

    @Test
    public void getCelsiusTemperatureTest_RxJava() {
        TemperatureObservable.getCelsiusTemperature("New York").blockingSubscribe(new TemperatureObserver());
    }

    @Test
    public void quiz17_2() {
        Observable<TempInfo> observable = TemperatureObservable.getTemperature("New York");
        observable.filter((TempInfo tempInfo) -> tempInfo.getTemp()<=0).blockingSubscribe(new TemperatureObserver());
    }

    @Test
    public void getCelsiusTemperaturesTest_RxJava() {
        Observable<TempInfo> celsiusTemperatures = TemperatureObservable.getCelsiusTemperatures(new String[]{"New York", "Chicago", "San Francisco"});
        celsiusTemperatures.blockingSubscribe(new TemperatureObserver());
    }
}