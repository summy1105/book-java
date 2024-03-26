package modernaction.chap10;

import org.junit.jupiter.api.Test;

import static modernaction.chap10.NestedFunctionOrderBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void methodChainingTest() {
        // 단점 : 지정된 순서로 호출해야함
        Order order = Order.MethodChainingOrderBuilder.forCustomer("BigBank")
                .buy(80)
                    .stock("IBM")
                    .on("NYSE")
                    .at(125.00)
                .sell(50)
                    .stock("GOOGLE")
                    .on("NASDAQ")
                    .at(375.00)
                .end();
    }

    @Test
    public void nestedFunctionOrderBuilderTest() {
        Order order = order("BigBank"
                , buy(80, stock("IBM", on("NYSE")), at(125.00))
                , sell(50, stock("GOOGLE", on("NASDAQ")), at(375.00))
        );
    }
}