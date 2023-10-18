package bg.sirma.roombooking.exception;

public class UserExistException extends Exception {
    public UserExistException(String userAlreadyExist) {
        super(userAlreadyExist);
    }
}
