package modernaction.chap10;

import java.util.function.DoubleUnaryOperator;

public class Tax {
    public static double regional(double value) {
        return value * 1.1;
    }

    public static double general(double value) {
        return value * 1.3;
    }

    public static double surcharge(double value) {
        return value * 1.05;
    }

    public static double calculate(Order order, boolean useRegional
            , boolean useGeneral, boolean useSurcharge) {
        double value = order.getValue();
        if(useGeneral) value = Tax.regional(value);
        if(useGeneral) value = Tax.general(value);
        if(useSurcharge) value = Tax.surcharge(value);

        return value;
    }

    public static class TaxCalculator{
        public DoubleUnaryOperator taxFunction = DoubleUnaryOperator.identity();

        public TaxCalculator with(DoubleUnaryOperator f){
            taxFunction = taxFunction.andThen(f);
            return this;
        }

        public double calculate(Order order) {
            return taxFunction.applyAsDouble(order.getValue());
        }
    }
}
