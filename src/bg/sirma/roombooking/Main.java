package bg.sirma.roombooking;

import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.service.RoomService;
import bg.sirma.roombooking.service.impl.RoomServiceImpl;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static final RoomService roomService = new RoomServiceImpl();
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = reader.readLine();
            while (!line.equals("end")) {
                switch (line) {
                    case "ViewRoom" -> {
                        Room[] rooms = roomService.viewRooms();
                    }
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
