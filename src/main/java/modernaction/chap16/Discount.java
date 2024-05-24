package modernaction.chap16;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Discount {
    @Getter
    @RequiredArgsConstructor
    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20)
        ;

        private final int percentage;
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is "
                + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        for (int i = 0; i < 100; i++) Shop.delay();
        return ((double) Math.round( price * (100 - code.getPercentage()) )) / 100;
    }

    public static List<String> findPrices(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop -> shop.getPriceAsString(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    private final Executor executor = Executors.newFixedThreadPool(8, (runnable) -> {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    });

    public static List<String> findPricesAsync(List<Shop> shops, String product) {
        Executor executor = new Discount().executor;
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceAsString(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                ))// flatMap 에 비유됨 function interface 의 return 객체가 CompletionStage(CompletableFuture).
                // thenApply는 map에 비유됨
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }


    //------------------------------------------------------------------------------------------------------------------

    public static List<String> findPrices_randomDelay(List<Shop> shops, String product) {
        Executor executorDeliverer = new Discount().executor;
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice_randomDelay(product), executorDeliverer))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorDeliverer)
                ))
                .collect(Collectors.toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static Stream<CompletableFuture<String>> findPricesStream(List<Shop> shops, String product) {
        Executor executorDeliverer = new Discount().executor;
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice_randomDelay(product), executorDeliverer))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorDeliverer)
                ));
    }
}
