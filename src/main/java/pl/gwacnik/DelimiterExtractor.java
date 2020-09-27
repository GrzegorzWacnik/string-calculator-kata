package pl.gwacnik;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

class DelimiterExtractor {

    public static final String DELIMITER_WRAPPER = "//|[\\[\\]]|\\n";

    static String extractFrom(String input) {
        return findDelimiter(input)
                .map(DelimiterExtractor::removeDefinitionWrapper)
                .map(escapeForRegex())
                .orElse(Delimiter.Patterns.DEFAULT_DELIMITER.getValue());
    }

    private static Optional<String> findDelimiter(String input) {
        return Pattern.compile(Delimiter.Patterns.DELIMITER_DEFINITION.getValue())
                .matcher(input)
                .results()
                .map(MatchResult::group)
                .findFirst();
    }

    private static String removeDefinitionWrapper(String d) {
        return d.replaceAll(DELIMITER_WRAPPER, "");
    }

    private static Function<String, String> escapeForRegex() {
        return Pattern::quote;
    }
}
