package bg.sirma.roombooking.service;

import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.User;

import java.io.IOException;

public interface UserService {
    void register(String username, String password) throws IOException;

    User login(String username, String password) throws IOException, UserNotFoundException;
}
