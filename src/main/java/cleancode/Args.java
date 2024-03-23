package cleancode;

import cleancode.marshaler.*;

import java.util.*;

import static cleancode.ArgsException.ErrorCode;

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
        parseArgumentStrings();
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
        else if ( elementTail.equals("[*]"))
            marshalers.put(elementId, new StringArrayArgumentMarshaler());
        else {
            throw new ArgsException(elementId, elementTail, ErrorCode.INVALID_ARGUMENT_FORMAT); // todo tail???
        }
    }

    private void validateSchemaElementId(char elementId) throws ArgsException{
        if (!Character.isLetter(elementId)) {
            throw new ArgsException(elementId, null, ErrorCode.INVALID_ARGUMENT_FORMAT);
        }
    }

    private void parseArgumentStrings() throws ArgsException {
        for (currentArgument = argList.iterator(); currentArgument.hasNext(); ) {
            String arg = currentArgument.next();
            if(arg.startsWith("-")){
                parseArgumentCharacters(arg.substring(1));
            } else {
                break;
            }
        }
    }

    private void parseArgumentCharacters(String arg) throws ArgsException {
        for (int i = 0; i < arg.length(); i++) {
            parseArgumentCharacter(arg.charAt(i));
        }
    }

    private void parseArgumentCharacter(char argChar) throws ArgsException {
        ArgumentMarshaler m = marshalers.get(argChar);
        if(m==null){
            throw new ArgsException(argChar, null, ErrorCode.UNEXPECTED_ARGUMENT);
        }
        argsFound.add(argChar);
        try{
            m.set(currentArgument);
        }catch (ArgsException e){
            e.setErrorArgumentId(argChar);
            throw e;
        }
    }

    public int cardinality() {
        return argsFound.size();
    }

    public String getString(char arg) {
        return StringArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public int getInt(char arg) {
        return IntegerArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public boolean getBoolean(char arg) {
        return BooleanArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public double getDouble(char arg) {
        return DoubleArgumentMarshaler.getValue(marshalers.get(arg));
    }

    public String[] getStringArray(char arg) {
        return StringArrayArgumentMarshaler.getValue( marshalers.get(arg));
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }
}
