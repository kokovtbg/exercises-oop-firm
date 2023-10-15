package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.RoomFileNotFoundException;
import bg.sirma.roombooking.model.Room;

import java.io.IOException;

public interface RoomService {
    Room[] viewFreeRooms() throws IOException, RoomFileNotFoundException;
}
