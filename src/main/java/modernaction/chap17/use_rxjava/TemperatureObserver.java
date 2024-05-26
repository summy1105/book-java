package modernaction.chap17.use_rxjava;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import modernaction.chap17.flow_api.TempInfo;

public class TemperatureObserver implements Observer<TempInfo> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull TempInfo tempInfo) {
        System.out.println(tempInfo);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        System.out.println("Got problem: "+e.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }
}
