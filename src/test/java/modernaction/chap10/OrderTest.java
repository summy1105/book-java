package modernaction.chap10;

import modernaction.chap10.builder.LambdaOrderBuilder;
import modernaction.chap10.builder.MixedBuilder;
import modernaction.chap10.builder.methodchain.MethodChainingOrderBuilder;
import org.junit.jupiter.api.Test;

import static modernaction.chap10.builder.NestedFunctionOrderBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void methodChainingTest() {
        // 단점 : 지정된 순서로 호출해야함
        Order order = MethodChainingOrderBuilder.forCustomer("BigBank")
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

    @Test
    public void lambdaOrderBuilderTest() {
        Order order = LambdaOrderBuilder.order(builder -> {
            builder.forCustomers("BigBank");
            builder.buy(tb -> {
                tb.quantity(80);
                tb.price(125.00);
                tb.stock(sb -> {
                    sb.symbol("IBM");
                    sb.market("NYSE");
                });
            });
            builder.sell(tb -> {
                tb.quantity(50);
                tb.price(375.00);
                tb.stock(sb -> {
                    sb.symbol("GOOGLE");
                    sb.market("NASDAQ");
                });
            });
        });
    }

    @Test
    public void mixedBuilderTest() {
        Order order = MixedBuilder.forCustomer("BigBank"
                , MixedBuilder.buy(tradeBuilder ->
                        tradeBuilder.quantity(80)
                                .stock("IBM")
                                .on("NYSE")
                                .at(125.00)
                )
                , MixedBuilder.sell(tb ->
                        tb.quantity(50)
                                .stock("GOOGLE")
                                .on("NASDAQ")
                                .at(125.00)
                )
        );

        double calculate = new Tax.TaxCalculator().with(Tax::regional)
                .with(Tax::general)
                .calculate(order);
        System.out.println("calculate = " + calculate);
        assertEquals((125+125)*1.1*1.3, calculate);
    }
}