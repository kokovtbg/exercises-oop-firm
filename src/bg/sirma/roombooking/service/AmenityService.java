package bg.sirma.roombooking.service;

import bg.sirma.roombooking.model.Amenity;

import java.io.IOException;

public interface AmenityService {
    Amenity[] getAllFromFile() throws IOException;

    void writeAllToFile(Amenity[] amenities) throws IOException;
}
