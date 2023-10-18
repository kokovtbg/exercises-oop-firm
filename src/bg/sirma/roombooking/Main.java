package bg.sirma.roombooking;

import bg.sirma.roombooking.exception.*;
import bg.sirma.roombooking.model.Amenity;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.HotelService;
import bg.sirma.roombooking.service.RoomService;
import bg.sirma.roombooking.service.UserService;
import bg.sirma.roombooking.service.impl.HotelServiceImpl;
import bg.sirma.roombooking.service.impl.RoomServiceImpl;
import bg.sirma.roombooking.service.impl.UserServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static final RoomService roomService = new RoomServiceImpl();
    private static final HotelService hotelService = new HotelServiceImpl();
    private static final UserService userService = new UserServiceImpl();
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
                            Room[] rooms = roomService.viewFreeRooms();
                            Arrays.stream(rooms)
                                    .forEach(System.out::println);
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
                            System.out.println(roomService.createRoom(currentUser, number, type, price, cancellationFee, hotelName, amenities));
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
                    }
                } catch (RoomFileNotFoundException | HotelNotFoundException | UserNotOwnerException |
                         RoomTypeNotFoundException | UserNotFoundException | UserExistException |
                         HotelExistException e) {
                    System.out.println(e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Must contain more parameters");
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
