package bg.sirma.roombooking.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private Set<Booking> bookingHistory;
    private Set<Hotel> hotels;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookingHistory = new LinkedHashSet<>();
        this.hotels = new LinkedHashSet<>();
    }


}
