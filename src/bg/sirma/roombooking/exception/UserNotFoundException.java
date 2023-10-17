package bg.sirma.roombooking.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String format) {
        super(format);
    }
}
