package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Room class represents a room in a hotel or similar accommodation.
 * It stores information such as room number, number of beds, availability, dates, and notes.
 */
public class Room {
    private int number, numberOfBeds, numberOfGuests;
    private boolean available;
    private LocalDate dateFrom, dateTo;
    private String note;
    private final List<Event> events;

    /**
     * Constructs a Room object with the specified parameters.
     *
     * @param number         The room number.
     * @param numberOfBeds   The number of beds in the room.
     * @param numberOfGuests The number of guests currently occupying the room.
     * @param available      Indicates whether the room is available or not.
     * @param dateFrom       The start date of the room reservation.
     * @param dateTo         The end date of the room reservation.
     * @param note           Additional notes about the room.
     */
    public Room(int number, int numberOfBeds, int numberOfGuests, boolean available, LocalDate dateFrom, LocalDate dateTo, String note) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.numberOfGuests = numberOfGuests;
        this.available = available;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
        this.events = new ArrayList<>();
    }

    /**
     * Retrieves the list of events associated with this room.
     *
     * @return The list of events.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Adds an event to the room's event list.
     *
     * @param e The event to be added.
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Removes an event from the room's event list.
     *
     * @param e The event to be removed.
     */
    public void removeEvent(Event e) {
        events.remove(e);
    }

    /**
     * Gets the room number.
     *
     * @return The room number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the room number.
     *
     * @param number The room number to set.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets the number of beds in the room.
     *
     * @return The number of beds.
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the number of beds in the room.
     *
     * @param numberOfBeds The number of beds to set.
     */
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Gets the number of guests currently occupying the room.
     *
     * @return The number of guests.
     */
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    /**
     * Sets the number of guests currently occupying the room.
     *
     * @param numberOfGuests The number of guests to set.
     */
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    /**
     * Checks if the room is available.
     *
     * @return True if the room is available, false otherwise.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Sets the availability of the room.
     *
     * @param available The availability to set.
     */
    public void setAvailable(boolean available) {
        this.available = available;
        if (this.available) {
            this.dateFrom = null;
            this.dateTo = null;
            this.numberOfGuests = 0;
            this.note = null;
        }
    }

    /**
     * Gets the start date of the room reservation.
     *
     * @return The start date.
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the start date of the room reservation.
     *
     * @param dateFrom The start date to set.
     */
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Gets the end date of the room reservation.
     *
     * @return The end date.
     */
    public LocalDate getDateTo() {
        return dateTo;
    }

    /**
     * Sets the end date of the room reservation.
     *
     * @param dateTo The end date to set.
     */
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Gets additional notes about the room.
     *
     * @return The notes.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional notes about the room.
     *
     * @param note The notes to set.
     */
    public void setNote(String note) {
        this.note = note;
    }
}
