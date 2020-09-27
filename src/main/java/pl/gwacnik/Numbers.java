package pl.gwacnik;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Numbers {
    public static final IntPredicate NEGATIVE_NUMBER_PREDICATE = n -> n < 0;
    public static final IntPredicate LESS_OR_EQUAL_1K = n -> n <= 1000;
    private final String[] numbers;

    Numbers(String[] numbers) {
        this.numbers = numbers;
    }

    boolean haveNegatives() {
        return intStream().anyMatch(NEGATIVE_NUMBER_PREDICATE);
    }

    String getNegativesAsString() {
        return intStream().filter(NEGATIVE_NUMBER_PREDICATE).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    Integer sum() {
        return intStream().filter(LESS_OR_EQUAL_1K).sum();
    }

    private IntStream intStream() {
        return Arrays.stream(numbers).mapToInt(Integer::valueOf);
    }
}
