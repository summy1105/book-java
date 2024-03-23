package cleancode;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.util.*;

import static cleancode.ArgsException.*;

public class Args {
    private String schema;
    private List<String> argList;
    private Iterator<String> currentArgument;
    private Map<Character, ArgumentMarshaler> marshalers = new HashMap<>();
    private Set<Character> argsFound = new HashSet<>();


    public Args(String schema, String[] args) throws ArgsException{
        this.schema = schema;
        argList = Arrays.asList(args);
        parse();
    }

    private void parse() throws ArgsException {
        parseSchema();
        parseArguments();
    }

    private boolean parseSchema() throws ArgsException {
        for (String element : schema.split(",")) {
            if (element.length() > 0) {
                parseSchemaElement(element.trim());
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ArgsException{
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if( elementTail.length() == 0 )
            marshalers.put(elementId, new BooleanArgumentMarshaler());
        else if ( elementTail.equals("*") )
            marshalers.put(elementId, new StringArgumentMarshaler());
        else if ( elementTail.equals("#") )
            marshalers.put(elementId, new IntegerArgumentMarshaler());
        else if ( elementTail.equals("##") )
            marshalers.put(elementId, new DoubleArgumentMarshaler());
        else {
            throw new ArgsException(elementId, elementTail, ErrorCode.INVALID_FORMAT); // todo tail???
        }
    }

    private void validateSchemaElementId(char elementId) throws ArgsException{
        if (!Character.isLetter(elementId)) {
            throw new ArgsException(elementId, null, ErrorCode.INVALID_ARGUMENT_NAME);
        }
    }

    private void parseArguments() throws ArgsException {
        for (currentArgument = argList.iterator(); currentArgument.hasNext(); ) {
            String arg = currentArgument.next();
            parseArgument(arg);
        }
    }

    private void parseArgument(String arg) throws ArgsException {
        if (arg.startsWith("-")) {
            parseElements(arg);
        }
    }

    private void parseElements(String arg) throws ArgsException {
        for (int i = 1; i < arg.length(); i++) {
            parseElement(arg.charAt(i));
        }
    }

    private void parseElement(char argChar) throws ArgsException{
        if (setArgument(argChar)) {
            argsFound.add(argChar);
        } else {
            throw new ArgsException(argChar, null, ErrorCode.UNEXPECTED_ARGUMENT);
        }
    }

    private boolean setArgument(char argChar) throws ArgsException {
        ArgumentMarshaler m = marshalers.get(argChar);
        if(m==null) return false;
        try{
            m.set(currentArgument);
            return true;
        }catch (ArgsException e){
            e.setErrorArgumentId(argChar);
            throw e;
        }
    }

    public int cardinality() {
        return argsFound.size();
    }

    public String usage() {
        if (schema.length() > 0)
            return "-[" + schema + "]";
        else return "";
    }

    public String getString(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try{
            return am == null ? "": (String)am.get();
        }catch (ClassCastException e){
            return "";
        }
    }

    public int getInt(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try{
            return am == null ? 0: (Integer)am.get();
        }catch (ClassCastException e){
            return 0;
        }
    }

    public boolean getBoolean(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try{
            return am != null && (Boolean) am.get();
        }catch (ClassCastException e){
            return false;
        }
    }

    public double getDouble(char arg) {
        ArgumentMarshaler am = marshalers.get(arg);
        try{
            return am == null ? 0.0 : (Double)am.get();
        }catch (ClassCastException e){
            return 0.0;
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    private interface ArgumentMarshaler{
        void set(Iterator<String> currentArgument) throws ArgsException;
        Object get();
    }

    private class BooleanArgumentMarshaler implements ArgumentMarshaler {
        protected boolean booleanValue = false;
        @Override
        public void set(Iterator<String> currentArgument) {
            booleanValue = true;
        }

        @Override
        public Object get() {
            return booleanValue;
        }
    }

    private class StringArgumentMarshaler implements ArgumentMarshaler {
        protected String stringValue;
        @Override
        public void set(Iterator<String> currentArgument) throws ArgsException {
            try {
                this.stringValue = currentArgument.next();
            } catch (NoSuchElementException e) {
                throw new ArgsException(ErrorCode.MISSING_STRING);
            }
        }

        @Override
        public Object get() {
            return stringValue == null ? "":stringValue;
        }
    }

    private class IntegerArgumentMarshaler implements ArgumentMarshaler {
        protected int integerValue;
        @Override
        public void set(Iterator<String> currentArgument) throws ArgsException{
            String parameter = null;
            try{
                parameter = currentArgument.next();
                this.integerValue = Integer.parseInt(parameter);
            } catch (NumberFormatException e){
                throw new ArgsException(parameter, ErrorCode.INVALID_INTEGER);
            } catch (NoSuchElementException e) {
                throw new ArgsException(parameter, ErrorCode.MISSING_INTEGER);
            }
        }

        @Override
        public Object get() {
            return integerValue;
        }
    }

    private class DoubleArgumentMarshaler implements ArgumentMarshaler {
        protected double doubleValue;
        @Override
        public void set(Iterator<String> currentArgument) throws ArgsException{
            String parameter = null;
            try{
                parameter = currentArgument.next();
                this.doubleValue = Double.parseDouble(parameter);
            } catch (NumberFormatException e){
                throw new ArgsException(parameter, ErrorCode.INVALID_DOUBLE);
            } catch (NoSuchElementException e) {
                throw new ArgsException(parameter, ErrorCode.MISSING_DOUBLE);
            }
        }

        @Override
        public Object get() {
            return doubleValue;
        }
    }
}
