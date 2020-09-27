package pl.gwacnik;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class Delimiter {

    public static final String DEFAULT_DELIMITER = ",|\\n";
    public static final String DELIMITER_DEFINITION = "//.*?\\n";

    private final String value;

    private Delimiter(String value) {
        this.value = value + "|" + DEFAULT_DELIMITER;
    }

    private Delimiter() {
        this.value = DEFAULT_DELIMITER;
    }

    static Delimiter fromString(String input) {
        if (input.startsWith("//[")) {
            final String delimiter = Pattern.compile(DELIMITER_DEFINITION)
                    .matcher(input)
                    .results()
                    .map(MatchResult::group)
                    .findFirst()
                    .map(d -> d.replaceAll("//|[\\[\\]]|\\n", ""))
                    .map(Pattern::quote)
                    .orElse(DEFAULT_DELIMITER);
            return new Delimiter(delimiter);

        } else if (input.startsWith("//")) {
            String customDelimiter = String.valueOf(input.charAt(2));
            return new Delimiter(customDelimiter);
        }

        return new Delimiter();
    }

    public String asRegex() {
        return value;
    }

    public String delimiterDef() {
        return DELIMITER_DEFINITION;
    }
}
