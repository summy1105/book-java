package cleancode.chap14.marshaler;

import java.util.Iterator;

public class BooleanArgumentMarshaler implements ArgumentMarshaler{
    protected boolean booleanValue = false;
    @Override
    public void set(Iterator<String> currentArgument) {
        booleanValue = true;
    }

    public static boolean getValue(ArgumentMarshaler am) {
        if(am != null && am instanceof BooleanArgumentMarshaler){
            return ((BooleanArgumentMarshaler) am).booleanValue;
        } else {
            return false;
        }
    }
}
