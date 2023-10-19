package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.*;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    Room createRoom(User currentUser,
                    int number,
                    String type,
                    BigDecimal price,
                    BigDecimal cancellationFee,
                    String hotelName,
                    String... amenities) throws IOException, HotelNotFoundException, UserNotOwnerException, RoomTypeNotFoundException, RoomExistException;

    Room getByNumberAndHotelName(int roomNumber, String hotelName) throws RoomNotFoundException;

    Room[] viewFreeRooms(LocalDate startDate, LocalDate endDate) throws DatesNotValidException, RoomFileNotFoundException;
}
