package bg.sirma.roombooking.exception;

public class HotelNotFoundException extends Exception {
    public HotelNotFoundException(String format) {
        super(format);
    }
}
