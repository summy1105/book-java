package cleancode.chap14.marshaler;

import cleancode.chap14.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringArgumentMarshaler implements ArgumentMarshaler {
    protected String stringValue;
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            this.stringValue = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaler am) {
        if(am != null && am instanceof StringArgumentMarshaler){
            return ((StringArgumentMarshaler) am).stringValue;
        } else {
            return "";
        }
    }
}