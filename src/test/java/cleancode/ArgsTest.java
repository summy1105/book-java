package cleancode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static cleancode.Args.*;
import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {

    @Test
    public void testCreateWithNoSchemaOrArguments() throws ParseException {
        Args args = new Args("", new String[0]);
        assertEquals(0, args.cardinality());
    }

    @Test
    public void testWithNoSchemaButWithOneArguments() throws ParseException {
        Args args = new Args("", new String[]{"-x"});
        assertEquals(ErrorCode.UNEXPECTED_ARGUMENT, args.getErrorCode());
    }

    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws ParseException {
        Args args = new Args("", new String[]{"-x", "-y"});
        assertEquals(ErrorCode.UNEXPECTED_ARGUMENT, args.getErrorCode());
    }

    @Test
    public void testNonLetterSchema() throws ParseException {
        assertThrows(ParseException.class, ()-> new Args("*", new String[]{}));
    }

    @Test
    public void testInvalidArgumentFormat() {
        assertThrows(ParseException.class, () -> new Args("f~", new String[]{}));
    }

    @Test
    public void testSimpleBooleanPresent() throws ParseException {
        Args args = new Args("x", new String[]{"-x"});
        assertEquals(1, args.cardinality());
        assertTrue(args.getBoolean('x'));
    }

    @Test
    public void testSimpleStringPresent() throws ParseException {
        Args args = new Args("x*", new String[]{"-x", "param"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals("param", args.getString('x'));
    }

    @Test
    public void testMissingStringArgument() throws ParseException {
        Args args = new Args("x*", new String[]{"-x"});
        assertEquals(ErrorCode.MISSING_STRING, args.getErrorCode());
    }

    @Test
    public void testSpacesInFormat() throws ParseException {
        Args args = new Args("x, y", new String[]{"-xy"});
        assertEquals(2, args.cardinality());
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
    }

    @Test
    public void testSimpleIntPresent() throws ParseException {
        Args args = new Args("x#", new String[]{"-x", "42"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
    }

    @Test
    public void testInvalidInteger() throws ParseException {
        Args args = new Args("x#", new String[]{"-x", "Forty two"});
        assertEquals(ErrorCode.INVALID_INTEGER, args.getErrorCode());
    }

    @Test
    public void testSimpleDoublePresent() {
//        Args args = new Args("x##", new String[]{"-x", "42.3"});
    }

    @Test
    public void testInvalidDouble() {
//        Args args = new Args("x##", new String[]{"-x", "Forty two"});
    }
    
    @Test
    public void testMissingDouble() {
//        Args args = new Args("x##", new String[]{"-x"});
    }
}