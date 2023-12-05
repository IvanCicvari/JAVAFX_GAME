package algebra.hr.gamejava.customExceptions;

import algebra.hr.gamejava.models.Card;

public class InvalidCountrySubmissionException extends Exception{
    private Card.Country expected;
    private Card.Country actual;
    public InvalidCountrySubmissionException(String message, Card.Country expected, Card.Country actual) {
        this.expected = expected;
        this.actual = actual;
    }

}