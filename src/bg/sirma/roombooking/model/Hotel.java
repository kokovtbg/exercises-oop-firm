package bg.sirma.roombooking.model;

import java.util.*;

public class Hotel {
    private final long id;
    private String name;
    private User owner;
//    private final Set<Room> rooms;

    public Hotel(long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
//        this.rooms = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

//    public Set<Room> getRooms() {
//        return Collections.unmodifiableSet(rooms);
//    }
//
//    public void addRoom(Room room) {
//        this.rooms.add(room);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.format("Hotel with name (%s)", this.getName());
    }
}
