package junit_test.mathers;

import org.junit.jupiter.api.Test;

import static junit_test.mathers.NumberAssert.assertThatNumber;

public class NumberAssertTest {
    @Test
    void testIsPositive() {
        assertThatNumber(10).isPositive(); // 성공
        assertThatNumber(-5).isNotPositive();
    }
}
