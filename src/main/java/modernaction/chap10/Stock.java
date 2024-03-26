package modernaction.chap10;

import lombok.Getter;
import lombok.Setter;

import static modernaction.chap10.Order.*;

@Setter
@Getter
public class Stock {
    private String symbol;
    private String market;

    public static class StockBuilder{

        private final MethodChainingOrderBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        public StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol(symbol);
        }

        public TradeBuilderWithStock on(String market) {
            stock.setMarket(market);
            trade.setStock(stock);
            return new TradeBuilderWithStock(builder, trade);
        }
    }
}
