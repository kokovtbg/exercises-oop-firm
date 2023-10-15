package bg.sirma.roombooking;

import bg.sirma.roombooking.exception.RoomFileNotFoundException;
import bg.sirma.roombooking.model.Amenity;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.service.RoomService;
import bg.sirma.roombooking.service.impl.RoomServiceImpl;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static final RoomService roomService = new RoomServiceImpl();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (!line.equals("end")) {
                switch (line) {
                    case "ViewFreeRooms" -> {
                        Room[] rooms = roomService.viewFreeRooms();
                        Arrays.stream(rooms)
                                .forEach(r -> System.out.printf("Hotel: %s, Type: %s, Price: %.2f, Amenities: %s",
                                        r.getHotel().getName(), r.getType(), r.getPrice(),
                                        r.getAmenities().stream().map(Amenity::getName).collect(Collectors.joining(", "))));
                    }
                }

                line = reader.readLine();
            }

        } catch (RoomFileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
