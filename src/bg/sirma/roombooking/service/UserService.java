package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.UserExistException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.User;

import java.io.IOException;

public interface UserService {
    User register(String username, String password) throws IOException, UserExistException;

    User login(String username, String password) throws IOException, UserNotFoundException;
}
