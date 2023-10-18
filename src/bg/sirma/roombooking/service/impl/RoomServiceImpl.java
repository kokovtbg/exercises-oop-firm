package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.exception.RoomFileNotFoundException;
import bg.sirma.roombooking.exception.RoomTypeNotFoundException;
import bg.sirma.roombooking.exception.UserNotOwnerException;
import bg.sirma.roombooking.model.*;
import bg.sirma.roombooking.service.AmenityService;
import bg.sirma.roombooking.service.HotelService;
import bg.sirma.roombooking.service.RoomService;
import bg.sirma.roombooking.service.RoomTypeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/savedFiles/";
    private static final String roomsPath = "rooms.json";
    private static final String hotelsPath = "hotels.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final HotelService hotelService = new HotelServiceImpl();
    private static final RoomTypeService roomTypeService = new RoomTypeServiceImpl();
    private static final AmenityService amenityService = new AmenityServiceImpl();

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
    public Room createRoom(User currentUser,
                           int number,
                           String type,
                           BigDecimal price,
                           BigDecimal cancellationFee,
                           String hotelName,
                           String... amenities) throws IOException, HotelNotFoundException, UserNotOwnerException, RoomTypeNotFoundException {
        Hotel hotel = hotelService.getByName(hotelName);
        if (!currentUser.equals(hotel.getOwner())) {
            throw new UserNotOwnerException(String.format("You are not owner of hotel with name %s", hotelName));
        }

        RoomType[] roomTypes = roomTypeService.getAllFromFile();
        RoomType roomTypeInDB = Arrays.stream(roomTypes)
                .filter(roomType -> roomType.getName().equals(type))
                .findFirst()
                .orElseThrow(() -> new RoomTypeNotFoundException("Room Type must be valid"));

        long lastRoomId = 0;
        try (Reader reader = Files.newBufferedReader(Path.of(basePath + roomsPath))) {
            Room[] rooms = gson.fromJson(reader, Room[].class);
            lastRoomId = Arrays.stream(rooms)
                    .map(Room::getId)
                    .mapToLong(Long::longValue)
                    .max()
                    .orElse(0);

        } catch (IOException ignored) {

        }

        if (lastRoomId == 0) {
            lastRoomId = 1;
        }

        Room room = new Room(lastRoomId, number, roomTypeInDB, price, cancellationFee, false, hotel);
        Amenity[] amenitiesFromFile = amenityService.getAllFromFile();
        Arrays.stream(amenitiesFromFile)
                .filter(amenity -> Arrays.stream(amenities).toList().contains(amenity.getName()))
                .forEach(room::addAmenity);
        Writer writer = Files.newBufferedWriter(Path.of(basePath + roomsPath));
        gson.toJson(room, writer);
        writer.close();

        hotel.addRoom(room);
        writer = Files.newBufferedWriter(Path.of(basePath + hotelsPath));
        gson.toJson(hotel, writer);
        writer.close();

        return room;
    }
}
