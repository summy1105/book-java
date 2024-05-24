package modernaction.chap16;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

class ShopTest {

    @Test
    void getPriceAsyncTest() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        //제품의 가격을 계산하는 동안
        doSomethingElse();

        try {
            double price = futurePrice.get(); // 가격 정보를 받을 때까지 블록된다.
            System.out.printf("Price is %.2f%n ", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    private void doSomethingElse() {
        try {
            Thread.sleep(800L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    List<Shop> shops = Arrays.asList(new Shop("BestPrice")
            , new Shop("LetsSaveBig")
            , new Shop("MyFavoriteShop")
            , new Shop("BuyItAll")
            , new Shop("BuyItAll2")
            , new Shop("BuyItAll3")
            , new Shop("BuyItAll4")
            , new Shop("BuyItAll5")
            , new Shop("BuyItAll6"));

    @Test
    public void findPriceTest() {
        long start = System.nanoTime();

        System.out.println(Shops.findPrice(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    }


    @Test
    public void findPriceParallelTest() {
        long start = System.nanoTime();
        System.out.println(Shops.findPrice2(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    } // 2464

    @Test
    public void findPriceCompletableFutureTest() {
        long start = System.nanoTime();
        System.out.println(Shops.findPrice3(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    } // 2443, 2439, 2449

    @Test
    public void findPriceCompletableFutureWithExecutorTest() {
        long start = System.nanoTime();
        System.out.println(Shops.findPrice_executor(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    }
    // thread 2 : 4885, 4857, 4862
    // thread 8 : 1220, 1219, 1222

    @Test
    public void discountTest() {
        long start = System.nanoTime();
        System.out.println(Discount.findPrices(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    } // 19464

    @Test
    public void discountAsyncTest() {
        long start = System.nanoTime();
        System.out.println(Discount.findPricesAsync(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    } // thread 8, shop 8개 : 2419, 2416, 2454
    // thread 8, shop 9개 : 3659

    @Test
    public void getPriceInUSDTest() throws ExecutionException, InterruptedException {
        Shop shop = shops.get(0);
        long start = System.nanoTime();
        System.out.println(shop.getPriceInUSD("myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    }

    @Test
    public void findPrices_randomDelay_test() {
        Shop shop = shops.get(0);
        long start = System.nanoTime();
        System.out.println("\n"+Discount.findPrices_randomDelay(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    }


    @Test
    public void findPricesStream_test() {
        Shop shop = shops.get(0);
        long start = System.nanoTime();
        CompletableFuture[] futures = Discount.findPricesStream(shops, "myPhone27S")
                .map(f -> f.thenAccept(s-> System.out.println( s + " (done in "
                        + ((System.nanoTime() - start) / 1_000_000)+ " msecs)")))
                .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    }
}