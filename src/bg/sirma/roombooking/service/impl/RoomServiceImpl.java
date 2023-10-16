package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.exception.RoomFileNotFoundException;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.RoomService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/savedFiles/";
    private static final String roomsPath = "rooms.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Room[] viewFreeRooms() throws IOException, RoomFileNotFoundException {
        try(BufferedReader reader = new BufferedReader(new FileReader(basePath + roomsPath))) {
            Room[] rooms = gson.fromJson(reader, Room[].class);
            return Arrays.stream(rooms)
                    .filter(r -> !r.isBooked())
                    .sorted(Comparator.comparing(r -> r.getHotel().getName()))
                    .toArray(Room[]::new);
        } catch (FileNotFoundException e) {
            throw new RoomFileNotFoundException("No rooms! Must first create one!");
        }
    }

    @Override
    public void createRoom(User currentUser, int number, String type, BigDecimal price, BigDecimal cancellationFee, String hotelName, List<String> amenities) {

    }
}
