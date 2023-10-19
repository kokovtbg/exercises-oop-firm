package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.deserializer.LocalDateDeserializer;
import bg.sirma.roombooking.exception.*;
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
    private static final String bookingsPath = "bookings.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .setPrettyPrinting()
            .create();
    private static final RoomService roomService = new RoomServiceImpl();

    @Override
    public Booking createBooking(User currentUser, LocalDate startDate, LocalDate endDate, int roomNumber, String hotelName) throws UserNotFoundException, IOException, RoomNotFoundException, DatesNotValidException {
        if (currentUser == null) {
            throw new UserNotFoundException("User does not exist");
        }

        if (startDate.isAfter(endDate) || startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new DatesNotValidException("Start date and End date must be valid");
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

    @Override
    public Booking[] reportBookings(User currentUser) throws RoomFileNotFoundException {
        try (Reader reader = Files.newBufferedReader(Path.of(path + bookingsPath))) {
            Booking[] bookings = gson.fromJson(reader, Booking[].class);
            return Arrays.stream(bookings)
                    .filter(b -> !b.isCancelled())
                    .toArray(Booking[]::new);
        } catch (IOException e) {
            throw new RoomFileNotFoundException("Nothing to show");
        }
    }

    @Override
    public Booking[] reportCancelledBookings(User currentUser) throws RoomFileNotFoundException {
        try (Reader reader = Files.newBufferedReader(Path.of(path + bookingsPath))) {
            Booking[] bookings = gson.fromJson(reader, Booking[].class);
            return Arrays.stream(bookings)
                    .filter(Booking::isCancelled)
                    .toArray(Booking[]::new);
        } catch (IOException e) {
            throw new RoomFileNotFoundException("Nothing to show");
        }
    }

    @Override
    public Booking cancelBooking(User currentUser, int id) throws RoomFileNotFoundException, UserNotFoundException, BookingNotFoundException, IOException {
        Booking[] bookings;
        try (Reader reader = Files.newBufferedReader(Path.of(path + bookingsPath))) {
            bookings = gson.fromJson(reader, Booking[].class);
            Arrays.stream(bookings)
                    .map(Booking::getBooker)
                    .filter(b -> b.equals(currentUser))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User not owner!!!"));
            Booking booking = Arrays.stream(bookings)
                    .filter(b -> b.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new BookingNotFoundException(String.format("Booking with id (%d) not found!!!", id)));
            booking.cancel();

        } catch (IOException e) {
            throw new RoomFileNotFoundException("Something went wrong");
        }

        Writer writer = Files.newBufferedWriter(Path.of(path + bookingsPath));
        gson.toJson(bookings, writer);
        writer.close();
        return null;
    }

}
