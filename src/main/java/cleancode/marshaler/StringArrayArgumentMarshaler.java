package cleancode.marshaler;

import cleancode.ArgsException;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.PatternSyntaxException;

public class StringArrayArgumentMarshaler implements ArgumentMarshaler {
    protected String[] stringArrayValue;
    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String strArray = null;
        try {
            strArray = currentArgument.next();
            stringArrayValue = strArray.split(",");
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING_ARRAY);
        } catch (PatternSyntaxException e){
            throw new ArgsException(strArray, ArgsException.ErrorCode.INVALID_STRING_ARRAY);
        }
    }

    public static String[] getValue(ArgumentMarshaler am) {
        if(am != null && am instanceof StringArrayArgumentMarshaler){
            return ((StringArrayArgumentMarshaler) am).stringArrayValue;
        } else {
            return new String[]{};
        }
    }
}