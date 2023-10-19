package bg.sirma.roombooking.exception;

public class RoomExistException extends Exception {
    public RoomExistException(String format) {
        super(format);
    }
}
