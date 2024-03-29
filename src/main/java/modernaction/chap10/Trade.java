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
}
