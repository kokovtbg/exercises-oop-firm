package bg.sirma.roombooking.exception;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException(String format) {
        super(format);
    }
}
