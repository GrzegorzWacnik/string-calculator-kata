package pl.gwacnik;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DelimiterExtractor {

    public static final String DELIMITER_WRAPPER = "//|[\\[\\]]|\\n";
    public static final String DELIMITERS_SEPARATOR = "]\\[";

    static List<String> extractFrom(String input) {
        return findDelimiters(input)
                .map(DelimiterExtractor::removeDefinitionWrapper)
                .map(escapeForRegex())
                .collect(Collectors.toList());
    }

    private static Stream<String> findDelimiters(String input) {
        return Pattern.compile(Delimiter.Patterns.DELIMITER_DEFINITION.getValue())
                .matcher(input)
                .results()
                .map(MatchResult::group)
                .flatMap(separateDelimiters());
    }

    private static String removeDefinitionWrapper(String d) {
        return d.replaceAll(DELIMITER_WRAPPER, "");
    }

    private static Function<String, String> escapeForRegex() {
        return Pattern::quote;
    }

    private static Function<String, Stream<? extends String>> separateDelimiters() {
        return delimiters -> Arrays.stream(delimiters.split(DELIMITERS_SEPARATOR));
    }
}
