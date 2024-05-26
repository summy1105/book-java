package modernaction.chap17.use_rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Consumer;
import modernaction.chap17.flow_api.TempInfo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TemperatureObservable {
    // Observable = Publisher
    public static Observable<TempInfo> getTemperature(String town) {
        return Observable.create((ObservableEmitter<TempInfo> emitter)->{ // Emitter = Processor 역할???

            // Observable.subscribe에서 onNext, onError, onComplete, onSubscribe 람다 혹은 Observer instance를 넘겨줌
            Consumer<Long> onNext = (Long n)-> {
                if (!emitter.isDisposed()) {
                    if (n >= 5) {
                        emitter.onComplete();
                    } else {
                        try {
                            emitter.onNext(TempInfo.fetch(town));
                        } catch (Exception e) {
                            emitter.onError(e);
                        }
                    }
                }
            }; // end onNext ->  Emitter를 감사는 두번 째 Emitter(Processor)

            Observable.interval(1, TimeUnit.SECONDS)
                    .subscribe(onNext); // onNext(processor)를 실행하는 Disposable(Subscription)생성???
        });
    }

    public static Observable<TempInfo> getCelsiusTemperature(String town) {
        Observable<TempInfo> publisher = getTemperature(town);
        return publisher.map((TempInfo temperature)-> {
            int fahrenheit = (temperature.getTemp() - 32) * 5 / 9;
            return new TempInfo(temperature.getTown(), fahrenheit);
        });
    }

    public static Observable<TempInfo> getCelsiusTemperatures(String... towns) {
        return Observable.merge(Arrays.stream(towns)
                .map(TemperatureObservable::getCelsiusTemperature)
                .collect(Collectors.toList()));
    }
}
