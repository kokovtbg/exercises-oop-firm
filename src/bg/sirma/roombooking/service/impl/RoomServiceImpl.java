package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.service.RoomService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/savedFiles/";
    private static final String roomsPath = "rooms.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Room[] viewRooms() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(basePath + roomsPath));
        Room[] rooms = gson.fromJson(reader, Room[].class);
        return Arrays.stream(rooms)
                .sorted(Comparator.comparing(r -> r.getHotel().getName()))
                .toArray(Room[]::new);
    }
}
