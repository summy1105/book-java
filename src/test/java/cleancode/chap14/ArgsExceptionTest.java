package cleancode.chap14;

import cleancode.chap14.ArgsException;
import org.junit.jupiter.api.Test;

import static cleancode.chap14.ArgsException.*;
import static org.junit.jupiter.api.Assertions.*;

class ArgsExceptionTest {

    @Test
    public void testUnexpectedMessage() throws Exception {
        ArgsException e = new ArgsException('x', null, ErrorCode.MISSING_STRING);
        assertEquals("Could not find string parameter for -x.", e.errorMessage());
    }

    @Test
    public void testInvalidIntegerMessage() throws Exception {
        ArgsException e = new ArgsException('x', "Forty two", ErrorCode.INVALID_INTEGER);
        assertEquals("Argument -x expects an integer but was 'Forty two'.", e.errorMessage());
    }

    @Test
    public void testMissingIntegerMessage() throws Exception {
        ArgsException e = new ArgsException('x', null, ErrorCode.MISSING_INTEGER);
        assertEquals("Could not find integer parameter for -x.", e.errorMessage());
    }

    @Test
    public void testInvalidDoubleMessage() throws Exception {
        ArgsException e = new ArgsException('x', "Forty two", ErrorCode.INVALID_DOUBLE);
        assertEquals("Argument -x expects a double but was 'Forty two'.", e.errorMessage());
    }

    @Test
    public void testMissingDoubleMessage() throws Exception {
        ArgsException e = new ArgsException('x', null, ErrorCode.MISSING_DOUBLE);
        assertEquals("Could not find double parameter for -x.", e.errorMessage());
    }
}