package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.Hotel;
import bg.sirma.roombooking.model.Room;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.HotelService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class HotelServiceImpl implements HotelService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/";
    private static final String savedHotelsPath = "savedFiles/hotels.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public Hotel getByName(String name) throws IOException, HotelNotFoundException {
        Hotel[] hotels = getAllFromFile();
        return Arrays.stream(hotels)
                .filter(h -> h.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new HotelNotFoundException(String.format("Hotel with name %s not found", name)));
    }

    @Override
    public Hotel[] getAllFromFile() throws IOException, HotelNotFoundException {
        try(Reader reader = Files.newBufferedReader(Path.of(basePath + savedHotelsPath))) {
            return gson.fromJson(reader, Hotel[].class);
        } catch (FileNotFoundException e) {
            throw new HotelNotFoundException("There are no hotels still");
        }
    }

    @Override
    public void createHotel(User currentUser, String hotelName) throws IOException, UserNotFoundException {
        if (currentUser == null) {
            throw new UserNotFoundException("Invalid user");
        }

        long lastHotelId = 0;
        try (Reader reader = Files.newBufferedReader(Path.of(basePath + savedHotelsPath))) {
            Hotel[] hotels = gson.fromJson(reader, Hotel[].class);
            lastHotelId = Arrays.stream(hotels)
                    .map(Hotel::getId)
                    .mapToLong(Long::longValue)
                    .max()
                    .orElse(0);

        } catch (IOException ignored) {

        }

        if (lastHotelId == 0) {
            lastHotelId = 1;
        }

        Hotel hotel = new Hotel(lastHotelId, hotelName, currentUser);
        Writer writer = Files.newBufferedWriter(Path.of(basePath + savedHotelsPath));
        gson.toJson(hotel, writer);
        writer.close();
    }
}
