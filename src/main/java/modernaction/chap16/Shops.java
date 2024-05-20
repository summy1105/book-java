package modernaction.chap16;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Shops {
    public static List<String> findPrice(List<Shop> shops, String product) {
        return shops.stream()
                .map(shop->String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public static List<String> findPrice2(List<Shop> shops, String product) {
        return shops.parallelStream()
                .map(shop->String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    public static List<String> findPrice3(List<Shop> shops, String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getShopName() + " price is " + shop.getPrice(product)))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
