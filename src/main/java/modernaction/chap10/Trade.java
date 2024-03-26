package modernaction.chap10;

import lombok.Getter;
import lombok.Setter;

import static modernaction.chap10.Order.*;
import static modernaction.chap10.Stock.*;

@Getter
@Setter
public class Trade {
    public enum Type {BUY, SELL}
    private Type type;

    private Stock stock;
    private int quantity;
    private double price;

    public static class TradeBuilder {
        private final MethodChainingOrderBuilder builder;
        public final Trade trade = new Trade();

        public TradeBuilder(MethodChainingOrderBuilder builder, Type type, int quantity) {
            this.builder = builder;
            trade.setType(type);
            trade.setQuantity(quantity);
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder(builder, trade, symbol);
        }
    }
}
