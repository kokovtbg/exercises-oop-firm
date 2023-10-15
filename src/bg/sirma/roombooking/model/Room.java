package bg.sirma.roombooking.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Room {
    private final long id;
    private final int number;
    private final RoomType type;
    private final Set<Amenity> amenities;
    private BigDecimal price;
    private BigDecimal cancellationFee;
    private boolean isBooked;
    private final Hotel hotel;

    public Room(long id,
                int number,
                RoomType type,
                BigDecimal price,
                BigDecimal cancellationFee,
                boolean isBooked,
                Hotel hotel) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.price = price;
        this.cancellationFee = cancellationFee;
        this.isBooked = isBooked;
        this.hotel = hotel;
        this.amenities = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getCancellationFee() {
        return cancellationFee;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public Set<Amenity> getAmenities() {
        return Collections.unmodifiableSet(amenities);
    }

    public void addAmenity(Amenity amenity) {
        this.amenities.add(amenity);
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number && Objects.equals(hotel, room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, hotel);
    }
}
