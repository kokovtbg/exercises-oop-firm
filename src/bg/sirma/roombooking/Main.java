package bg.sirma.roombooking;

import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.exception.RoomFileNotFoundException;
import bg.sirma.roombooking.exception.RoomTypeNotFoundException;
import bg.sirma.roombooking.exception.UserNotOwnerException;
import bg.sirma.roombooking.model.Amenity;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.RoomService;
import bg.sirma.roombooking.service.impl.RoomServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final RoomService roomService = new RoomServiceImpl();
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
                                    .forEach(r -> System.out.printf("Hotel: %s, Type: %s, Price: %.2f, Amenities: %s",
                                            r.getHotel().getName(), r.getType(), r.getPrice(),
                                            r.getAmenities().stream().map(Amenity::getName).collect(Collectors.joining(", "))));
                        }
                        case "CreateRoom" -> {
                            int number = Integer.parseInt(commandData[1]);
                            String type = commandData[2];
                            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(commandData[3]));
                            BigDecimal cancellationFee = BigDecimal.valueOf(Double.parseDouble(commandData[4]));
                            String hotelName = commandData[5];
                            List<String> amenities = Arrays.stream(commandData[6].split("\\s*,\\s*")).toList();
                            roomService.createRoom(currentUser, number, type, price, cancellationFee, hotelName, amenities);
                        }
                    }
                } catch (RoomFileNotFoundException |
                         HotelNotFoundException |
                         UserNotOwnerException |
                         RoomTypeNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
