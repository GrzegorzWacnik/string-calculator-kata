package pl.gwacnik;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringCalculator
{
    public static final String DEFAULT_DELIMITER = "[,\\n]";

    public Integer add(String input) {
        if(input.isEmpty())
            return 0;

        if(input.startsWith("//")){
            String delimiter = String.valueOf(input.charAt(2));
            return numbersFrom(input.replaceAll("//.*?\\n", ""), delimiter).sum();
        }

        return numbersFrom(input).sum();
    }

    private IntStream numbersFrom(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter)).mapToInt(Integer::valueOf);
    }

    private IntStream numbersFrom(String input) {
        return numbersFrom(input, DEFAULT_DELIMITER);
    }
}
