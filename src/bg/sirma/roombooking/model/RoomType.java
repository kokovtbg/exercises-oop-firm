package bg.sirma.roombooking.model;

import java.util.Objects;

public class RoomType {
    private final String name;

    public RoomType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(name, roomType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
