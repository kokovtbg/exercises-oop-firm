package bg.sirma.roombooking.exception;

public class RoomTypeNotFoundException extends Exception {
    public RoomTypeNotFoundException(String roomTypeMustBeValid) {
        super(roomTypeMustBeValid);
    }
}
