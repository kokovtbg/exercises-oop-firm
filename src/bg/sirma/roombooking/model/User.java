package bg.sirma.roombooking.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private final Set<Booking> bookingHistory;
    private final Set<Hotel> hotels;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
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

    @Override
    public String toString() {
        return String.format("User with username (%s) and password (%s)", this.getUsername(), this.getPassword());
    }
}
