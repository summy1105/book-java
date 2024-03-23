package cleancode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static cleancode.ArgsException.ErrorCode;
import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {

    @Test
    public void testCreateWithNoSchemaOrArguments() throws ArgsException {
        Args args = new Args("", new String[0]);
        assertEquals(0, args.cardinality());
    }

    @Test
    public void testWithNoSchemaButWithOneArguments() throws ArgsException {
        try {
            new Args("", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }

    }

    @Test
    public void testWithNoSchemaButWithMultipleArguments() throws ArgsException {
        try {
            new Args("", new String[]{"-x", "-y"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.UNEXPECTED_ARGUMENT, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testNonLetterSchema() throws ArgsException {
        try {
            new Args("*", new String[]{});
            fail("Args constructor should have thrown exception");
        } catch (ArgsException e) {
            assertEquals(ErrorCode.INVALID_ARGUMENT_FORMAT, e.getErrorCode());
            assertEquals('*', e.getErrorArgumentId());
        }
    }

    @Test
    public void testInvalidArgumentFormat() {
        try {
            new Args("f~", new String[]{});
            fail("Args constructor should have throws exception");
        } catch (ArgsException e) {
            assertEquals(ErrorCode.INVALID_ARGUMENT_FORMAT, e.getErrorCode());
            assertEquals('f', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSimpleBooleanPresent() throws ArgsException {
        Args args = new Args("x", new String[]{"-x"});
        assertEquals(1, args.cardinality());
        assertTrue(args.getBoolean('x'));
        assertFalse(args.getBoolean('y'));
    }

    @Test
    public void testSimpleStringPresent() throws ArgsException {
        Args args = new Args("x*", new String[]{"-x", "param"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertFalse(args.has('y'));
        assertEquals("param", args.getString('x'));
        assertEquals("", args.getString('y'));
    }

    @Test
    public void testMissingStringArgument() throws ArgsException {
        try {
            new Args("x*", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.MISSING_STRING, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSpacesInFormat() throws ArgsException {
        Args args = new Args("x, y", new String[]{"-xy"});
        assertEquals(2, args.cardinality());
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
        assertTrue(args.getBoolean('x'));
        assertTrue(args.getBoolean('y'));
    }

    @Test
    public void testSimpleIntPresent() throws ArgsException {
        Args args = new Args("x#", new String[]{"-x", "42"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42, args.getInt('x'));
    }

    @Test
    public void testInvalidInteger() throws ArgsException {
        try {
            new Args("x#", new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.INVALID_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals ("Forty two", e.getErrorParameter());
        }
    }

    @Test
    public void testMissingInteger() throws Exception {
        try {
            new Args("x#", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.MISSING_INTEGER, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSimpleDoublePresent() throws ArgsException {
        Args args = new Args("x##", new String[]{"-x", "42.3"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        assertEquals(42.3, args.getDouble('x'));
    }

    @Test
    public void testInvalidDouble() {
        try {
            new Args("x##" , new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.INVALID_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }
    }
    
    @Test
    public void testMissingDouble() throws Exception {
        try {
            new Args("x##", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.MISSING_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }

    @Test
    public void testSimpleStringArrayPresent() throws ArgsException {
        Args args = new Args("x[*]", new String[]{"-x", "param,parameter,test"});
        assertEquals(1, args.cardinality());
        assertTrue(args.has('x'));
        String[] stringArray = args.getStringArray('x');
        assertTrue(Arrays.stream(stringArray).anyMatch(x->x.equals("param")));
        assertTrue(Arrays.stream(stringArray).anyMatch(x->x.equals("parameter")));
        assertTrue(Arrays.stream(stringArray).anyMatch(x->x.equals("test")));
    }

    @Test
    public void testInvalidStringArray() {
        try {
            new Args("x##" , new String[]{"-x", "Forty two"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.INVALID_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
            assertEquals("Forty two", e.getErrorParameter());
        }
    }

    @Test
    public void testMissingStringArray() throws Exception {
        try {
            new Args("x##", new String[]{"-x"});
            fail();
        } catch (ArgsException e) {
            assertEquals(ErrorCode.MISSING_DOUBLE, e.getErrorCode());
            assertEquals('x', e.getErrorArgumentId());
        }
    }


    @Test
    public void testTwicePresent() throws ArgsException {
        Args args = new Args("x*, y##", new String[]{"-xy", "param", "42.3"});
        assertEquals(2, args.cardinality());
        assertTrue(args.has('x'));
        assertTrue(args.has('y'));
        assertEquals("param", args.getString('x'));
        assertEquals(42.3, args.getDouble('y'));
    }
}