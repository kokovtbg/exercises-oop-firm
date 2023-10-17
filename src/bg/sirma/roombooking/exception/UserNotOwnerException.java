package bg.sirma.roombooking.exception;

public class UserNotOwnerException extends Exception {
    public UserNotOwnerException(String format) {
        super(format);
    }
}
