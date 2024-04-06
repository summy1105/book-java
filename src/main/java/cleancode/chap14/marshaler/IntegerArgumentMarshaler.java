package cleancode.chap14.marshaler;

import cleancode.chap14.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    protected int integerValue;
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try{
            parameter = currentArgument.next();
            this.integerValue = Integer.parseInt(parameter);
        } catch (NumberFormatException e){
            throw new ArgsException(parameter, ArgsException.ErrorCode.INVALID_INTEGER);
        } catch (NoSuchElementException e) {
            throw new ArgsException(parameter, ArgsException.ErrorCode.MISSING_INTEGER);
        }
    }

    public static int getValue(ArgumentMarshaler am) {
        if(am != null && am instanceof IntegerArgumentMarshaler){
            return ((IntegerArgumentMarshaler) am).integerValue;
        } else {
            return 0;
        }
    }
}