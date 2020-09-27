package pl.gwacnik;

class NegativeNumbersNotSupported extends RuntimeException{

    public NegativeNumbersNotSupported(String message) {
        super(message);
    }
}
