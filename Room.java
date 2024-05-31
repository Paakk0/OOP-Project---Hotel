package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int number, numberOfBeds;
    private String note;
    private List<Event> events;
    private List<Reservation> reservations;

    public Room(int number, int numberOfBeds, String note) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.note = note;
        this.events = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public boolean checkIfAvailable(LocalDate from, LocalDate to) {
        if (this.reservations.isEmpty()) {
            return true;
        }
        for (Reservation reservation : this.reservations) {
            if ((from.isBefore(reservation.getTo()) && to.isAfter(reservation.getFrom())) ||
                    from.isEqual(reservation.getFrom()) || to.isEqual(reservation.getTo())) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(Reservation r) {
        this.reservations.add(r);
    }

    public void removeReservation(Reservation r) {
        this.reservations.remove(r);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public void removeEvent(Event e) {
        events.remove(e);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
