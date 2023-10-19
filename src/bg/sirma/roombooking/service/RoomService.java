package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.*;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface RoomService {
    Room[] viewFreeRooms() throws IOException, RoomFileNotFoundException;

    Room createRoom(User currentUser,
                    int number,
                    String type,
                    BigDecimal price,
                    BigDecimal cancellationFee,
                    String hotelName,
                    String... amenities) throws IOException, HotelNotFoundException, UserNotOwnerException, RoomTypeNotFoundException;

    Room getByNumberAndHotelName(int roomNumber, String hotelName) throws RoomNotFoundException;
}
