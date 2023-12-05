package algebra.hr.gamejava.customExceptions;

import algebra.hr.gamejava.models.Card;

public class InvalidValueOfCardSubmissionException extends Exception{
    private Card.ValueOfCard expected;
    private Card.ValueOfCard actual;
    public InvalidValueOfCardSubmissionException(String message, Card.ValueOfCard expected, Card.ValueOfCard actual) {
        this.expected = expected;
        this.actual = actual;
    }

}