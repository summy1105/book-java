package modernaction.chap20.pattern_match;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BinOp extends Expr {
    String opName;
    Expr left;
    Expr right;
}
