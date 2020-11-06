package aval.ua.conference.exception;

public class TalkAlreadyExistException extends  RuntimeException{

    public TalkAlreadyExistException(String message) {
        super(message);
    }
}
