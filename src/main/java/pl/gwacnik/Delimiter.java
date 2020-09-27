package pl.gwacnik;

class Delimiter {
    private final String value;

    private Delimiter(String value) {
        this.value = value + "|" + Patterns.DEFAULT_DELIMITER.getValue();
    }

    private Delimiter() {
        this.value = Patterns.DEFAULT_DELIMITER.getValue();
    }

    static Delimiter fromString(String input) {
        if (hasCustomDelimiter(input)) {
            final String delimiter = DelimiterExtractor.extractFrom(input);
            return new Delimiter(delimiter);
        }
        return new Delimiter();
    }

    private static boolean hasCustomDelimiter(String input) {
        return input.startsWith("//");
    }

    public String asRegex() {
        return value;
    }

    public String delimiterDef() {
        return Patterns.DELIMITER_DEFINITION.getValue();
    }

    enum Patterns {
        DEFAULT_DELIMITER(",|\\n"),
        DELIMITER_DEFINITION("//.*?\\n");

        private final String value;

        Patterns(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
