package bg.sirma.roombooking.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Booking> getBookingHistory() {
        return Collections.unmodifiableSet(bookingHistory);
    }

    public Set<Hotel> getHotels() {
        return Collections.unmodifiableSet(hotels);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
