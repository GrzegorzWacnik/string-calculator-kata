package pl.gwacnik;

class Delimiter {

    public static final String DEFAULT_DELIMITER = "[,\\n]";

    private final String value;

    public Delimiter(String value) {
        this.value = value;
    }

    static Delimiter fromString(String input) {
        if(input.startsWith("//")) {
            String customDelimiter = String.valueOf(input.charAt(2));
            return new Delimiter(customDelimiter);
        }

        return new Delimiter(DEFAULT_DELIMITER);
    }

    public String asRegex() {
        return value;
    }

    public String delimiterDef() {
        return "//.*?\\n";
    }
}
