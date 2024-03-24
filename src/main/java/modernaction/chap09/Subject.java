package modernaction.chap09;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObservers(String tweet);

    class Feed implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer o) {
            observers.add(o);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(o->o.notify(tweet));
        }
    }
}
