package bg.sirma.roombooking.model;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private final long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private User booker;
    private Room room;

    public Booking(long id, LocalDate startDate, LocalDate endDate, User booker, Room room) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.booker = booker;
        this.room = room;
    }

    public long getId() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking book = (Booking) o;
        return Objects.equals(startDate, book.startDate) && Objects.equals(endDate, book.endDate) && Objects.equals(booker, book.booker) && Objects.equals(room, book.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, booker, room);
    }

}
