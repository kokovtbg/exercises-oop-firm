package bg.sirma.roombooking.service.impl;

import bg.sirma.roombooking.deserializer.LocalDateDeserializer;
import bg.sirma.roombooking.exception.UserExistException;
import bg.sirma.roombooking.exception.UserNotFoundException;
import bg.sirma.roombooking.model.User;
import bg.sirma.roombooking.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String path = "src/bg/sirma/roombooking/resources/json/savedFiles/";
    private static final String usersPath = "users.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .setPrettyPrinting()
            .create();

    @Override
    public User register(String username, String password) throws IOException, UserExistException {
        User[] users = new User[0];
        try (Reader reader = Files.newBufferedReader(Path.of(path + usersPath))) {
            users = gson.fromJson(reader, User[].class);
            String usernameInDB = Arrays.stream(users)
                    .map(User::getUsername)
                    .filter(uname -> uname.equals(username))
                    .findFirst()
                    .orElse(null);
            if (usernameInDB != null) {
                throw new UserExistException("User Already exist");
            }
        } catch (FileNotFoundException | NoSuchFileException ignored) {

        }

        Writer writer = Files.newBufferedWriter(Path.of(path + usersPath));
        User user = new User(username, password);
        List<User> newUsers = new ArrayList<>(List.of(users));
        newUsers.add(user);
        gson.toJson(newUsers.toArray(), writer);
        writer.close();

        return user;
    }

    @Override
    public User login(String username, String password) throws IOException, UserNotFoundException {
        try (Reader reader = Files.newBufferedReader(Path.of(path + usersPath))) {
            User[] users = gson.fromJson(reader, User[].class);
            return Arrays.stream(users)
                    .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException(String.format("User with username (%s) and password (%s) does not exist", username, password)));
        } catch (FileNotFoundException | NoSuchFileException e) {
            throw new UserNotFoundException(String.format("User with username (%s) and password (%s) does not exist", username, password));
        }

    }
}
