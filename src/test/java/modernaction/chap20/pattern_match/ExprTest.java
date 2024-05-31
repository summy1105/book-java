package modernaction.chap20.pattern_match;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExprTest {

    @Test
    public void simplifyTest() {
        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = Expr.simplify(e);
        System.out.println("match = " + (Number)match);
    }
}