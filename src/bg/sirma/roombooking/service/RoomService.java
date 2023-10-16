package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.RoomFileNotFoundException;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface RoomService {
    Room[] viewFreeRooms() throws IOException, RoomFileNotFoundException;

    void createRoom(User currentUser, int number, String type, BigDecimal price, BigDecimal cancellationFee, String hotelName, List<String> amenities);
}
