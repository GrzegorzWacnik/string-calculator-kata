package pl.gwacnik;

import java.util.stream.Collectors;

public class StringCalculator
{
    public Integer add(String input) {
        if(input.isEmpty())
            return 0;

        if(InputParser.fromString(input).parseToStream().anyMatch(n -> n < 0)) {
            final String negativeNumbers = InputParser.fromString(input).parseToStream().filter(n -> n < 0).mapToObj(String::valueOf).collect(Collectors.joining(","));
            throw new NegativeNumbersNotSupported(negativeNumbers);
        }

        return InputParser.fromString(input).parseToStream().sum();
    }
}
