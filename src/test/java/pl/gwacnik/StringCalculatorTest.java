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
}