package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.RoomNotFoundException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.Booking;
import bg.sirma.roombooking.model.User;

import java.io.IOException;
import java.time.LocalDate;

public interface BookingService {
    Booking createBooking(User currentUser, LocalDate startDate, LocalDate endDate, int roomNumber, String hotelName) throws UserNotFoundException, IOException, RoomNotFoundException;
}
