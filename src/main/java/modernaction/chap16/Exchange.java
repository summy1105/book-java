package modernaction.chap16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class Exchange {
    @Getter
    @AllArgsConstructor
    public enum Money{
        EUR(1), USD(1.08)
        ;
        private double oneEuro;
    }

    public static double DEFAULT_RATE = 1.0;
    public static double getRate(Money firstCode, Money secondCode) {
        for (int i = 0; i < 10; i++) {
            Shop.delay();
        }
        return secondCode.getOneEuro() / firstCode.getOneEuro();
    }
}
