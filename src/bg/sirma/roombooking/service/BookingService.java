package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.*;
import bg.sirma.roombooking.model.Booking;
import bg.sirma.roombooking.model.User;

import java.io.IOException;
import java.time.LocalDate;

public interface BookingService {
    Booking createBooking(User currentUser, LocalDate startDate, LocalDate endDate, int roomNumber, String hotelName) throws UserNotFoundException, IOException, RoomNotFoundException, DatesNotValidException;

    Booking[] reportBookings(User currentUser) throws RoomFileNotFoundException;

    Booking[] reportCancelledBookings(User currentUser) throws RoomFileNotFoundException;

    Booking cancelBooking(User currentUser, int id) throws RoomFileNotFoundException, UserNotFoundException, BookingNotFoundException, IOException;
}
