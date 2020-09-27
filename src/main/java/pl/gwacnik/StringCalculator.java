package pl.gwacnik;

public class StringCalculator
{

    public Integer add(String input) {
        if(input.isEmpty())
            return 0;

        return Integer.valueOf(input);
    }
}
