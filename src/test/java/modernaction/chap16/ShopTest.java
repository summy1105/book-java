package modernaction.chap16;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
            , new Shop("BuyItAll"));

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
    }

    @Test
    public void findPriceCompletableFutureTest() {
        long start = System.nanoTime();
        System.out.println(Shops.findPrice3(shops, "myPhone27S"));

        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("duration (msec) = " + duration);
    }
}