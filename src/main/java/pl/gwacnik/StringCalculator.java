package pl.gwacnik;

public class StringCalculator
{
    public Integer add(String input) {
        if(input.isEmpty())
            return 0;

        final Numbers numbers = InputParser.fromString(input).parseNumbers();

        if(numbers.haveNegatives()) {
            negativeNumbersAreNotSupported(numbers);
        }

        return numbers.sum();
    }

    private void negativeNumbersAreNotSupported(Numbers numbers) {
        final String negativeNumbers = numbers.getNegativesAsString();
        throw new NegativeNumbersNotSupported(negativeNumbers);
    }
}
