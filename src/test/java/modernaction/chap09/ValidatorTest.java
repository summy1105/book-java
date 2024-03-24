package modernaction.chap09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    public void validateTest() {
        Validator numericValidator = new Validator(new Validator.IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        assertFalse(b1);

        Validator lowerCaseValidator = new Validator(new Validator.IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");
        assertTrue(b2);
    }

    @Test
    public void validateLambdaTest() {
        Validator numericValidator = new Validator((String s) -> s.matches("\\d+"));
        assertFalse(numericValidator.validate("aaaa"));

        Validator lowerCaseValidator = new Validator((String s) -> s.matches("[a-z]+"));
        assertTrue(lowerCaseValidator.validate("bbbb"));
    }

}