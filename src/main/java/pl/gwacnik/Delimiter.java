package pl.gwacnik;

class Delimiter {

    public static final String DEFAULT_DELIMITER = ",\\n";

    private final String value;

    private Delimiter(String value) {
        this.value = value + DEFAULT_DELIMITER;
    }

    private Delimiter() {
        this.value = DEFAULT_DELIMITER;
    }

    static Delimiter fromString(String input) {
        if(input.startsWith("//")) {
            String customDelimiter = String.valueOf(input.charAt(2));
            return new Delimiter(customDelimiter);
        }

        return new Delimiter();
    }

    public String asRegex() {
        return "[" + value + "]";
    }

    public String delimiterDef() {
        return "//.*?\\n";
    }
}
