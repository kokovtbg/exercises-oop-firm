package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.model.Hotel;

import java.io.IOException;

public interface HotelService {
    Hotel getByName(String name) throws IOException, HotelNotFoundException;

    Hotel[] getAllFromFile() throws IOException;
}
