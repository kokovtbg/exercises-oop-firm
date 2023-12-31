package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.HotelExistException;
import bg.sirma.roombooking.exception.HotelNotFoundException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.Hotel;
import bg.sirma.roombooking.model.User;

import java.io.IOException;

public interface HotelService {
    Hotel getByName(String name) throws IOException, HotelNotFoundException;

    Hotel[] getAllFromFile() throws IOException, HotelNotFoundException;

    Hotel createHotel(User currentUser, String hotelName) throws IOException, UserNotFoundException, HotelExistException;
}
