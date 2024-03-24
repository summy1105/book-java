package modernaction.chap09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedTest {

    @Test
    public void implementsTest() {
        Subject.Feed feed = new Subject.Feed();
        feed.registerObserver(new Observer.NYTimes());
        feed.registerObserver(new Observer.Guardian());
        feed.registerObserver(new Observer.LeMonde());
        feed.notifyObservers("The queen said her favourite book is Modern Java in Action...???");
        feed.notifyObservers("The queen said her favourite wine is red wine.");
        feed.notifyObservers("The queen said wind is too expensive, she has a lot of money.");
    }

    @Test
    public void lambdaTest() {
        Subject.Feed feed = new Subject.Feed();
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet more news from London... "+ tweet);
            }
        });
        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! "+ tweet);
            }
        });
        feed.notifyObservers("The queen said wind is too expensive, she has a lot of money.");
    }

}