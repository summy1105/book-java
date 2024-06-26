package cleancode.chap15;

public class ComparisonCompactor {
    private static final String ELLIPSIS = "...";
    private static final String DELTA_END = "]";
    private static final String DELTA_START = "[";
    private int contextLength;
    private String expected;
    private String actual;
    private int prefixLength;
    private int suffixLength;

    private String compactExpected;
    private String compactActual;
    /**
     *
     * @param contextLength 비교[]옆에 남겨둘 글자
     * @param expected
     * @param actual
     */
    public ComparisonCompactor(int contextLength, String expected, String actual) {
        this.contextLength = contextLength;
        this.expected = expected;
        this.actual = actual;
    }

    public String formatCompactedComparison(String message) {
        if (shouldNotBeCompacted()) {
            return FakeAssert.format(message, expected, actual);
        }

        findCommonPrefixAndSuffix();
        compactExpected= compactString(expected);
        compactActual = compactString(actual);
        return FakeAssert.format(message, compactExpected, compactActual);
    }

    private boolean shouldNotBeCompacted() {
        return expected == null || actual == null || expected.equals(actual);
    }

    private void findCommonPrefixAndSuffix() {
        findCommonPrefix();

        suffixLength = 0;
        for (; !suffixOverlapsPrefix(suffixLength);
             suffixLength ++ ) {
            if (charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength))
                break;
        }
    }

    private void findCommonPrefix() {
        prefixLength = 0;
        int end = Math.min(expected.length(), actual.length());
        for (; prefixLength < end; prefixLength++) {
            if (expected.charAt(prefixLength) != actual.charAt(prefixLength))
                break;
        }
    }

    private boolean suffixOverlapsPrefix(int suffixLength) {
        return actual.length() - suffixLength <= prefixLength
                || expected.length() - suffixLength <= prefixLength;
    }

    private char charFromEnd(String s, int i) {
        return s.charAt(s.length()-1 -i);
    }

    private String compactString(String source) {
        return new StringBuilder()
                .append(startingEllipsis())
                .append(startingContext())
                .append(DELTA_START)
                .append(delta(source))
                .append(DELTA_END)
                .append(endingContext())
                .append(endingEllipsis())
                .toString();
    }

    private String startingContext() {
        int contextStart = Math.max(0, prefixLength - contextLength);
        int contextEnd = prefixLength;
        return expected.substring(contextStart, contextEnd);
    }

    private String startingEllipsis(){
        return prefixLength > contextLength ? ELLIPSIS : "";
    }

    private String delta(String source) {
        int deltaStartIdx = prefixLength;
        int deltaEndIdx = source.length() - suffixLength;
        return source.substring(deltaStartIdx, deltaEndIdx);
    }

    private String endingContext() {
        int contextStart = expected.length() - suffixLength;
        int contextEnd = Math.min(contextStart + contextLength, expected.length());
        return expected.substring(contextStart, contextEnd);
    }

    private String endingEllipsis(){
        return suffixLength > contextLength ? ELLIPSIS : "";
    }
}
