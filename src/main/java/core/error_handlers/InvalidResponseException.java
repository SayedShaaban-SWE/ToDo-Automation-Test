package core.error_handlers;

public class InvalidResponseException extends Exception {
    public InvalidResponseException(String message){
        super(message);
    }
}
