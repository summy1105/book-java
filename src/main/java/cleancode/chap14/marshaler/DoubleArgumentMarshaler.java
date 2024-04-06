package cleancode.chap14.marshaler;

import cleancode.chap14.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
    protected double doubleValue;
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try{
            parameter = currentArgument.next();
            this.doubleValue = Double.parseDouble(parameter);
        } catch (NumberFormatException e){
            throw new ArgsException(parameter, ArgsException.ErrorCode.INVALID_DOUBLE);
        } catch (NoSuchElementException e) {
            throw new ArgsException(parameter, ArgsException.ErrorCode.MISSING_DOUBLE);
        }
    }

    public static double getValue(ArgumentMarshaler am) {
        if(am != null && am instanceof DoubleArgumentMarshaler){
            return ((DoubleArgumentMarshaler) am).doubleValue;
        } else {
            return 0.0;
        }
    }
}