package cleancode.chap15;

public class FakeAssert {
    static String format(String message, Object expected, Object actual) {
        String formatted = "";
        if (message != null && !"".equals(message)) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        if (equalsRegardingNull(expectedString, actualString)) {
            return formatted + "expected:"
                    + "<" + expectedString + ">"
                    + " but was:" +  "<" + actualString + ">";
        } else {
            return formatted + "expected:<" + expectedString + "> but was:<"
                    + actualString + ">";
        }
    }

    private static boolean equalsRegardingNull(Object expected, Object actual) {
        if (expected == null) {
            return actual == null;
        }

        return isEquals(expected, actual);
    }


    private static boolean isEquals(Object expected, Object actual) {
        return expected.equals(actual);
    }
}
