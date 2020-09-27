package pl.gwacnik;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DelimiterExtractor {

    public static final String DELIMITER_WRAPPER = "//|[\\[\\]]|\\n";

    static String extractFrom(String input) {
        return findDelimiters(input)
                .map(DelimiterExtractor::removeDefinitionWrapper)
                .map(escapeForRegex())
                .collect(Collectors.joining("|"));
    }

    private static Stream<String> findDelimiters(String input) {
        return Pattern.compile(Delimiter.Patterns.DELIMITER_DEFINITION.getValue())
                .matcher(input)
                .results()
                .map(MatchResult::group)
                .flatMap(delimiters -> Arrays.stream(delimiters.split("]\\[")));
    }

    private static String removeDefinitionWrapper(String d) {
        return d.replaceAll(DELIMITER_WRAPPER, "");
    }

    private static Function<String, String> escapeForRegex() {
        return Pattern::quote;
    }
}
