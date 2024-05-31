package modernaction.chap20.pattern_match;

import lombok.ToString;

import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.function.Supplier;

@ToString
public class Expr {


    static <T> T patternMatchExpr(Expr e
            , TriFunction<String, Expr, Expr, T> binOpCase
            , Function<Integer, T> numCase
            , Supplier<T> defaultCase) {
        return (e instanceof BinOp) ?
                    binOpCase.apply(((BinOp) e).opName, ((BinOp) e).left, ((BinOp) e).right) :
                (e instanceof Number) ?
                    numCase.apply(((Number) e).val) :
                // default
                    defaultCase.get();
    }

    public static Expr simplify(Expr expr) {
        TriFunction<String, Expr, Expr, Expr> binOpCase = (opName, left, right) ->{
            if("+".equals(opName)){
                if (left instanceof Number && ((Number) left).val == 0) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 0) {
                    return left;
                }
            }
            else if("*".equals(opName)){
                if (left instanceof Number && ((Number) left).val == 1) {
                    return right;
                }
                if (right instanceof Number && ((Number) right).val == 1) {
                    return left;
                }
            }
            return new BinOp(opName, left, right);
        };

        Function<Integer, Expr> numCase = val -> new Number(val);
        Supplier<Expr> defaultCase = () -> new Number(0);

        return patternMatchExpr(expr, binOpCase, numCase, defaultCase);
    }
}
