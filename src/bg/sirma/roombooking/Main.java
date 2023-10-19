package bg.sirma.roombooking;

import bg.sirma.roombooking.exception.*;
import bg.sirma.roombooking.model.Booking;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.BookingService;
import bg.sirma.roombooking.service.HotelService;
import bg.sirma.roombooking.service.RoomService;
import bg.sirma.roombooking.service.UserService;
import bg.sirma.roombooking.service.impl.BookingServiceImpl;
import bg.sirma.roombooking.service.impl.HotelServiceImpl;
import bg.sirma.roombooking.service.impl.RoomServiceImpl;
import bg.sirma.roombooking.service.impl.UserServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;


public class Main {
    private static final RoomService roomService = new RoomServiceImpl();
    private static final HotelService hotelService = new HotelServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final BookingService bookingService = new BookingServiceImpl();
    private static User currentUser = null;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (!line.equals("end")) {
                String[] commandData = line.split("\\s+");
                String command = commandData[0];
                try {
                    switch (command) {
                        case "ViewFreeRooms" -> {
                            LocalDate startDate = LocalDate.parse(commandData[1]);
                            LocalDate endDate = LocalDate.parse(commandData[2]);
                            Room[] rooms = roomService.viewFreeRooms(startDate, endDate);
                            if (rooms.length > 0) {
                                Arrays.stream(rooms).forEach(System.out::println);
                            } else {
                                System.out.println("There are no free rooms");
                            }

                        }
                        case "CreateRoom" -> {
                            int number = Integer.parseInt(commandData[1]);
                            String type = commandData[2];
                            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(commandData[3]));
                            BigDecimal cancellationFee = BigDecimal.valueOf(Double.parseDouble(commandData[4]));
                            String hotelName = commandData[5];
                            String[] amenities = new String[0];
                            if (commandData.length > 6) {
                                amenities = Arrays.stream(commandData[6].split("\\s*,\\s*")).toArray(String[]::new);
                            }
                            System.out.println(roomService.createRoom(currentUser,
                                    number,
                                    type,
                                    price,
                                    cancellationFee,
                                    hotelName,
                                    amenities) + " successfully created");
                        }
                        case "CreateHotel" -> {
                            String hotelName = commandData[1];
                            System.out.println(hotelService.createHotel(currentUser, hotelName) + " successfully created!!!");
                        }
                        case "Register" -> {
                            String username = commandData[1];
                            String password = commandData[2];
                            System.out.println(userService.register(username, password) + " successfully created!!!");
                        }
                        case "Login" -> {
                            String username = commandData[1];
                            String password = commandData[2];
                            currentUser = userService.login(username, password);
                            System.out.printf("Logged in successfully with (%s)%n", username);
                        }
                        case "Book" -> {
                            LocalDate startDate = LocalDate.parse(commandData[1]);
                            LocalDate endDate = LocalDate.parse(commandData[2]);
                            int roomNumber = Integer.parseInt(commandData[3]);
                            String hotelName = commandData[4];
                            System.out.println(bookingService.createBooking(currentUser, startDate, endDate, roomNumber, hotelName)
                                    + " successfully booked!!!");
                        }
                        case "ReportBooking" -> {
                            Arrays.stream(bookingService.reportBookings(currentUser)).forEach(System.out::println);
                        }
                        case "ReportCancellation" -> {
                            Arrays.stream(bookingService.reportCancelledBookings(currentUser)).forEach(System.out::println);
                        }
                        case "CancelBooking" -> {
                            int id = Integer.parseInt(commandData[1]);
                            bookingService.cancelBooking(currentUser, id);
                            System.out.printf("Successfully cancelled Booking with id (%d)%n", id);
                        }
                    }
                } catch (HotelNotFoundException | UserNotOwnerException | RoomTypeNotFoundException |
                         UserNotFoundException | UserExistException | HotelExistException | RoomNotFoundException |
                         DatesNotValidException | RoomFileNotFoundException | RoomExistException |
                         BookingNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Must contain more parameters");
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("Something went wrong!!! Type (ListCommands)");
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
