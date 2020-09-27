package pl.gwacnik;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest
{
    private final StringCalculator calculator = new StringCalculator();

    @Test
    public void testReturnZeroIfEmptyStringInput() {
        final Integer sum = calculator.add("");

        assertThat(sum).isEqualTo(0);
    }

    @Test
    public void testReturnNumberIfOneNumberInput() {
        final Integer sum = calculator.add("1");

        assertThat(sum).isEqualTo(1);
    }

    @Test
    public void testReturnSumOfTwoNumbersInput() {
        final Integer sum = calculator.add("1,2");

        assertThat(sum).isEqualTo(3);
    }

    @Test
    public void testReturnSumOfMultipleNumbers() {
        final Integer sum = calculator.add("1,2,3,4,5");

        assertThat(sum).isEqualTo(15);
    }
}