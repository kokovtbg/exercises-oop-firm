package bg.sirma.roombooking.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Hotel {
    private final long id;
    private String name;
    private User owner;
    private final Map<Room, Integer> rooms;

    public Hotel(long id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.rooms = new LinkedHashMap<>();
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

    public Map<Room, Integer> getRooms() {
        return Collections.unmodifiableMap(rooms);
    }

    public void addRoom(Room room) {
        if (!this.rooms.containsKey(room)) {
            this.rooms.put(room, 0);
        }
        this.rooms.put(room, this.rooms.get(room) + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
