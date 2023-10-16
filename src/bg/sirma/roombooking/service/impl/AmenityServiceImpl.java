package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.model.Amenity;
import bg.sirma.roombooking.model.RoomType;
import bg.sirma.roombooking.service.AmenityService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class AmenityServiceImpl implements AmenityService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/";
    private static final String roomTypesPath = "input/amenities.json";
    private static final String savedRoomTypesPath = "savedFiles/amenities.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Amenity[] getAllFromFile() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of(basePath + roomTypesPath));
        Amenity[] types = gson.fromJson(reader, Amenity[].class);
        reader.close();
        return types;
    }

    @Override
    public void writeAllToFile(Amenity[] amenities) throws IOException {
        Writer writer = Files.newBufferedWriter(Path.of(basePath + savedRoomTypesPath));
        gson.toJson(amenities, writer);
        writer.close();
    }
}
