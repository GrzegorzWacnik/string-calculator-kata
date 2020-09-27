package pl.gwacnik;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator
{
    public static final String DELIMITER = "[,\\n]";

    public Integer add(String input) {
        if(input.isEmpty())
            return 0;

        return numbersFrom(input).sum();
    }

    private IntStream numbersFrom(String input) {
        return Arrays.stream(input.split(DELIMITER)).mapToInt(Integer::valueOf);
    }
}
