package pl.gwacnik;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    public void testNewLineAsSeparator() {
        final Integer sum = calculator.add("1\n2,3");

        assertThat(sum).isEqualTo(6);
    }

    @Test
    public void testCustomDelimiterSupport() {
        final Integer sum = calculator.add("//;\n1;2");

        assertThat(sum).isEqualTo(3);
    }

    @Test
    public void testCustomDelimiterAndNewLineAsSeparator() {
        final Integer sum = calculator.add("//;\n1;2\n3");

        assertThat(sum).isEqualTo(6);
    }

    @Test
    public void testNoNegativeNumbersAreSupported() {
        //Marek - that's the only case I think use of 'var' makes sense
        //as type here is quite ugly: AbstractThrowableAssert<? extends AbstractThrowableAssert<?,?>, ?>
        var calculateException = assertThatThrownBy(() -> calculator.add("1,2,-3,-4"));

        calculateException.isInstanceOf(NegativeNumbersNotSupported.class);
        calculateException.hasMessage("-3,-4");
    }

    @Test
    public void testNumbersGreaterThan1kShouldBeSkipped() {
        final Integer sum = calculator.add("1001,2");

        assertThat(sum).isEqualTo(2);
    }

    @Test
    public void testSumNumbersLessOrEqual1k() {
        final Integer sum = calculator.add("1000,1");

        assertThat(sum).isEqualTo(1001);
    }

    @Test
    public void testSupportMultipleCharactersDelimiter() {
        final Integer sum = calculator.add("//[***]\n1***2***3");

        assertThat(sum).isEqualTo(6);
    }

    @Test
    public void testMultiCharDelimiterAndDefaultDelimitersSupport() {
        final Integer sum = calculator.add("//[***]\n1***2,3\n4");

        assertThat(sum).isEqualTo(10);
    }

    @Test
    public void testMultipleDelimitersSupport() {
        final Integer sum = calculator.add("//[*][%]\n1*2%3");

        assertThat(sum).isEqualTo(6);
    }
}