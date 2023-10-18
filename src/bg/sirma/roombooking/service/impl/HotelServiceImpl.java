package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.deserializer.LocalDateDeserializer;
import bg.sirma.roombooking.exception.HotelExistException;
import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.Hotel;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.HotelService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotelServiceImpl implements HotelService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/savedFiles/";
    private static final String savedHotelsPath = "hotels.json";
    private static final String usersPath = "users.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .setPrettyPrinting()
            .create();
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
        } catch (FileNotFoundException | NoSuchFileException e) {
            throw new HotelNotFoundException("There are no hotels still");
        }
    }

    @Override
    public Hotel createHotel(User currentUser, String hotelName) throws IOException, UserNotFoundException, HotelExistException {
        if (currentUser == null) {
            throw new UserNotFoundException("You must first login");
        }

        long lastHotelId = 0;
        Hotel[] hotels = new Hotel[0];
        try (Reader reader = Files.newBufferedReader(Path.of(basePath + savedHotelsPath))) {
            hotels = gson.fromJson(reader, Hotel[].class);
            String nameHotelFromDB = Arrays.stream(hotels)
                    .map(Hotel::getName)
                    .filter(e -> e.equals(hotelName))
                    .findFirst()
                    .orElse(null);
            if (nameHotelFromDB != null) {
                throw new HotelExistException(String.format("Hotel with name (%s) already exist", hotelName));
            }

            lastHotelId = Arrays.stream(hotels)
                    .map(Hotel::getId)
                    .mapToLong(Long::longValue)
                    .max()
                    .orElse(0);

        } catch (IOException ignored) {

        }

        if (lastHotelId == 0) {
            lastHotelId = 1;
        } else {
            lastHotelId++;
        }

        Hotel hotel = new Hotel(lastHotelId, hotelName, currentUser);
        List<Hotel> newHotels = new ArrayList<>(List.of(hotels));
        newHotels.add(hotel);
        Writer writer = Files.newBufferedWriter(Path.of(basePath + savedHotelsPath));
        gson.toJson(newHotels, writer);
        writer.close();

        Reader reader = Files.newBufferedReader(Path.of(basePath + usersPath));
        User[] users = gson.fromJson(reader, User[].class);
        reader.close();

        Arrays.stream(users)
                .filter(u -> u.equals(currentUser))
                .findFirst().ifPresent(user -> user.addHotel(hotel));
        writer = Files.newBufferedWriter(Path.of(basePath + usersPath));
        gson.toJson(users, writer);
        writer.close();

        return hotel;
    }
}
