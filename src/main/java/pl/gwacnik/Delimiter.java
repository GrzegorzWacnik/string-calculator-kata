package pl.gwacnik;

import java.util.List;

class Delimiter {
    private static final String REGEX_OR_OPERATOR = "|";
    private final String value;

    private Delimiter(List<String> delimiters) {
        delimiters.add(Patterns.DEFAULT_DELIMITER.value);
        this.value = String.join(REGEX_OR_OPERATOR, delimiters);
    }

    private Delimiter() {
        this.value = Patterns.DEFAULT_DELIMITER.getValue();
    }

    static Delimiter fromString(String input) {
        if (hasCustomDelimiter(input)) {
            return new Delimiter(DelimiterExtractor.extractFrom(input));
        }
        return new Delimiter();
    }

    private static boolean hasCustomDelimiter(String input) {
        return input.startsWith(Patterns.CUSTOM_DELIMITER_LINE_START.getValue());
    }

    public String asRegex() {
        return value;
    }

    public String delimiterDef() {
        return Patterns.DELIMITER_DEFINITION.getValue();
    }

    enum Patterns {
        DEFAULT_DELIMITER(",|\\n"),
        DELIMITER_DEFINITION("//.*?\\n"),
        CUSTOM_DELIMITER_LINE_START("//");

        private final String value;

        Patterns(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }
}
