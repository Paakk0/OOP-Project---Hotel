package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private LocalDate from, to;
    private List<Guest> guests;

    public Reservation(LocalDate from, LocalDate to, String identity, int numberOfGuests) {
        this.from = from;
        this.to = to;
        this.guests = new ArrayList<>();
        addGuest(new Guest(identity, numberOfGuests));
    }

    public Reservation(LocalDate from, LocalDate to, List<Guest> g) {
        this.from = from;
        this.to = to;
        this.guests = g;
    }


    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void addGuest(Guest g) {
        this.guests.add(g);
    }

    public void removeGuest(Guest g) {
        this.guests.remove(g);
    }

    public List<Guest> getGuests() {
        return guests;
    }
}
