package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.model.RoomType;
import bg.sirma.roombooking.service.RoomTypeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class RoomTypeServiceImpl implements RoomTypeService {
    private static final String basePath = "src/bg/sirma/roombooking/resources/json/";
    private static final String roomTypesPath = "input/roomType.json";
    private static final String savedRoomTypesPath = "savedFiles/roomTypes.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public RoomType[] getAllFromFile() throws IOException {
        Reader reader = Files.newBufferedReader(Path.of(basePath + roomTypesPath));
        RoomType[] types = gson.fromJson(reader, RoomType[].class);
        reader.close();
        return types;
    }

    @Override
    public void writeAllToFile(RoomType[] roomTypes) throws IOException {
        Writer writer = Files.newBufferedWriter(Path.of(basePath + savedRoomTypesPath));
        gson.toJson(roomTypes, writer);
        writer.close();
    }
}
