package bg.sirma.roombooking.service;

import bg.sirma.roombooking.model.RoomType;

import java.io.IOException;

public interface RoomTypeService {
    RoomType[] getAllFromFile() throws IOException;

    void writeAllToFile(RoomType[] roomTypes) throws IOException;
}
