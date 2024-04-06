package cleancode.chap14;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArgsException extends Exception {
    private char errorArgumentId = '\0';
    private String errorParameter = null;
    private ErrorCode errorCode = ErrorCode.OK;
    public enum ErrorCode{
        OK, MISSING_STRING
        , MISSING_INTEGER, INVALID_INTEGER
        , MISSING_DOUBLE, INVALID_DOUBLE
        , MISSING_STRING_ARRAY, INVALID_STRING_ARRAY
        , UNEXPECTED_ARGUMENT
        , INVALID_ARGUMENT_FORMAT
    }

    public ArgsException(String errorParameter, ErrorCode errorCode) {
        this.errorParameter = errorParameter;
        this.errorCode = errorCode;
    }

    public ArgsException(char errorArgumentId, String errorParameter, ErrorCode errorCode) {
        this.errorArgumentId = errorArgumentId;
        this.errorParameter = errorParameter;
        this.errorCode = errorCode;
    }

    public ArgsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }


    public String errorMessage() throws Exception {
        switch (errorCode) {
            case OK:
                throw new Exception("TILT: Should not get here.");
            case UNEXPECTED_ARGUMENT:
                return String.format("Argument -%c unexpected.", errorArgumentId);
            case MISSING_STRING:
                return String.format("Could not find string parameter for -%c.", errorArgumentId);
            case INVALID_INTEGER:
                return String.format("Argument -%c expects an integer but was '%s'.", errorArgumentId, errorParameter);
            case MISSING_INTEGER:
                return String.format("Could not find integer parameter for -%c.", errorArgumentId);
            case INVALID_DOUBLE:
                return String.format("Argument -%c expects a double but was '%s'.", errorArgumentId, errorParameter);
            case MISSING_DOUBLE:
                return String.format("Could not find double parameter for -%c.", errorArgumentId);
        }
        return "";
    }
}
