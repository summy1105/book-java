package modernaction.chap16;

import lombok.Getter;

import java.util.Random;
import java.util.concurrent.*;

import static modernaction.chap16.Exchange.*;

@Getter
public class Shop {
    private final String BLOCK_OUT_IDX = "12";

    private final String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public double getPrice(String product) {
        return calculatePrice(product+BLOCK_OUT_IDX);
    }

    public String getPriceAsString(String product) {
        double price = calculatePrice(product);
        Discount.Code[] codeValues = Discount.Code.values();
        Discount.Code code = codeValues[new Random().nextInt(codeValues.length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }
/*
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()->{ // 새로운 쓰레드에서 비동기적으로 실행
            try {
                double price = calculatePrice(product + BLOCK_OUT_IDX);
                futurePrice.complete(price);// 계산이 완료되면 Future에 값 설정
            } catch (Exception exception) {
                System.out.println("getPriceAsync Error");
                futurePrice.completeExceptionally(exception);
            }
        }).start();
        return futurePrice;
    }
 */

    public Future<Double> getPriceAsync(String product) { // 팩토리 메소드 사용
        return CompletableFuture.supplyAsync(() -> calculatePrice(product)); // d.completeThrowable(ex); 에러관리 함.
    }


    private double calculatePrice(String product) {
        for (int i = 0; i < 100; i++) delay();

        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPriceInUSD(String product) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> futurePriceInUSD = CompletableFuture.supplyAsync(() -> getPrice(product))
                .thenCombine(
                        CompletableFuture
                                .supplyAsync(() -> getRate(Money.EUR, Money.USD))
                                .completeOnTimeout(DEFAULT_RATE, 1, TimeUnit.SECONDS) // getRate가 1초안에 결과가 없으면 기본값 제공
                        , (Double price, Double rate) -> price * rate
                );
        return futurePriceInUSD.get();
    }

    //------------------------------------------------------------------------------------------------------------------

    public static final Random random = new Random();

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        System.out.print("delay = " + delay + "  ");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getPrice_randomDelay(String product) {
        double price = calculatePrice_randomDelay(product+BLOCK_OUT_IDX);
        Discount.Code[] codeValues = Discount.Code.values();
        Discount.Code code = codeValues[new Random().nextInt(codeValues.length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    private double calculatePrice_randomDelay(String product) {
        randomDelay();

        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
