package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.model.Hotel;
import bg.sirma.roombooking.service.HotelService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
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
}
