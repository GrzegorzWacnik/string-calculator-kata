package pl.gwacnik;

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

    Numbers parseNumbers(){
        return new Numbers(splitByDelimiter(input));
    }

    private String[] splitByDelimiter(String input) {
        return removeDelimiterDefinition(input).split(delimiter.asRegex());
    }

    private String removeDelimiterDefinition(String input) {
        return input.replaceAll(delimiter.delimiterDef(), "");
    }
}
