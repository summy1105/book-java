package modernaction.chap10;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static modernaction.chap10.Trade.*;

@Getter
@Setter
public class Order {
    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getPrice).sum();
    }
}
