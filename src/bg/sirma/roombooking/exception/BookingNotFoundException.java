package bg.sirma.roombooking.exception;

public class BookingNotFoundException extends Exception {
    public BookingNotFoundException(String format) {
        super(format);
    }
}
