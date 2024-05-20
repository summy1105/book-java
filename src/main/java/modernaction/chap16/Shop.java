package modernaction.chap16;

import lombok.Getter;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

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
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
