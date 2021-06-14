package hu.flowacedmy.kappaspringexam.exception;

public class ValidationException extends RuntimeException {

    public ValidationException (String message){
        super(message);
    }
}
