package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class UserServiceImpl implements UserService {
    private static final String path = "src/bg/sirma/roombooking/resources/json/savedFiles";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void register(String username, String password) throws IOException {
        User user = new User(username, password);
        Writer writer = Files.newBufferedWriter(Path.of(path));
        gson.toJson(user, writer);
        writer.close();
    }

    @Override
    public User login(String username, String password) throws IOException, UserNotFoundException {
        Reader reader = Files.newBufferedReader(Path.of(path));
        User[] users = gson.fromJson(reader, User[].class);
         return Arrays.stream(users)
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(String.format("User with username (%s) and password (%s) does not exist", username, password)));
    }
}
