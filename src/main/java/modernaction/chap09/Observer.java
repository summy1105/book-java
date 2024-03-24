package modernaction.chap09;

public interface Observer {
    void notify(String tweet);

    class NYTimes implements Observer {
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! "+ tweet);
            }
        }
    }

    class Guardian implements Observer{
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet more news from London... "+ tweet);
            }
        }
    }

    class LeMonde implements Observer{
        @Override
        public void notify(String tweet) {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! "+ tweet);
            }
        }
    }
}
