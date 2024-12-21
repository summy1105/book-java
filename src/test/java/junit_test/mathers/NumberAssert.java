package junit_test.mathers;

import org.assertj.core.api.AbstractAssert;

/**
 * assertj 를 사용한 matcher 구현
 */
public class NumberAssert extends AbstractAssert<NumberAssert, Integer> {

    public NumberAssert(Integer actual) {
        super(actual, NumberAssert.class);
    }

    public static NumberAssert assertThatNumber(Integer actual) {
        return new NumberAssert(actual);
    }

    public NumberAssert isPositive() {
        if (actual <= 0) {
            failWithMessage("Expected number to be positive, but was %d", actual);
        }
        return this;
    }

    public NumberAssert isNotPositive() {
        if (actual > 0) {
            failWithMessage("Expected number to be positive, but was %d", actual);
        }
        return this;
    }
}