package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.deserializer.LocalDateDeserializer;
import bg.sirma.roombooking.exception.RoomNotFoundException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.Booking;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.BookingService;
import bg.sirma.roombooking.service.RoomService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    private static final String path = "src/bg/sirma/roombooking/resources/json/savedFiles/";
    private static final String bookingsPath = "booking.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .setPrettyPrinting()
            .create();
    private static final RoomService roomService = new RoomServiceImpl();
    @Override
    public Booking createBooking(User currentUser, LocalDate startDate, LocalDate endDate, int roomNumber, String hotelName) throws UserNotFoundException, IOException, RoomNotFoundException {
        if (currentUser == null) {
            throw new UserNotFoundException("User does not exist");
        }

        long lastBookingId = 0;
        Booking[] bookings = new Booking[0];
        try (Reader reader = Files.newBufferedReader(Path.of(path + bookingsPath))) {
            bookings = gson.fromJson(reader, Booking[].class);
            if (bookings == null) {
                bookings = new Booking[0];
            }

            lastBookingId = Arrays.stream(bookings)
                    .map(Booking::getId)
                    .mapToLong(Long::longValue)
                    .max()
                    .orElse(0);
        } catch (IOException ignored) {

        }

        if (lastBookingId == 0) {
            lastBookingId = 1;
        } else {
            lastBookingId++;
        }

        Room room = roomService.getByNumberAndHotelName(roomNumber, hotelName);
        Booking booking = new Booking(lastBookingId, startDate, endDate, currentUser, room);
        List<Booking> newBookings = new ArrayList<>(List.of(bookings));
        newBookings.add(booking);
        Writer writer = Files.newBufferedWriter(Path.of(path + bookingsPath));
        gson.toJson(newBookings, writer);
        writer.close();

        return booking;
    }
}
