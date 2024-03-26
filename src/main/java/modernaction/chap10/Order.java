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

    public static class MethodChainingOrderBuilder{
        public final Order order = new Order();

        private MethodChainingOrderBuilder(String customer) {
            order.setCustomer(customer);
        }

        public static MethodChainingOrderBuilder forCustomer(String customer) {
            return new MethodChainingOrderBuilder(customer);
        }

        public TradeBuilder buy(int quantity) {
            return new TradeBuilder(this, Type.BUY, quantity);
        }

        public TradeBuilder sell(int quantity) {
            return new TradeBuilder(this, Type.SELL, quantity);
        }

        public MethodChainingOrderBuilder addTrade(Trade trade) {
            order.addTrade(trade);
            return this;
        }

        public Order end() {
            return order;
        }
    }
}
