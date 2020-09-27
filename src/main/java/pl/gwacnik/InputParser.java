package pl.gwacnik;

import java.util.Arrays;
import java.util.stream.IntStream;

class InputParser {

    private final String input;
    private final Delimiter delimiter;

    private InputParser(String input, Delimiter delimiter) {
        this.input = input;
        this.delimiter = delimiter;
    }

    static InputParser fromString(String input) {
        return new InputParser(input, Delimiter.fromString(input));
    }

    IntStream parseToStream(){
        return numbersFrom(input);
    }

    private IntStream numbersFrom(String input) {
        return Arrays.stream(splitByDelimiter(input)).mapToInt(Integer::valueOf);
    }

    private String[] splitByDelimiter(String input) {
        return removeDelimiterDefinition(input).split(delimiter.asRegex());
    }

    private String removeDelimiterDefinition(String input) {
        return input.replaceAll(delimiter.delimiterDef(), "");
    }
}
