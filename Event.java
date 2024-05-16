package Model;

/**
 * The Event enum represents different types of events that can be associated with a room.
 */
public enum Event {
    Conferences(1),
    Weddings(2),
    Banquets(3),
    Retreats(4),
    Expos(5),
    Workshops(6),
    Networking(7),
    Concerts(8),
    Fashion(9),
    Holidays(10),
    Fundraisers(11),
    Reunions(12),
    Wellness(13),
    Culinary(14);

    private final int id;

    /**
     * Constructs an Event enum with the specified ID.
     *
     * @param id The ID of the event.
     */
    Event(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the event.
     *
     * @return The ID of the event.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the Event enum associated with the specified ID.
     *
     * @param id The ID of the event to retrieve.
     * @return The Event enum corresponding to the ID.
     * @throws IllegalArgumentException if no event is found with the given ID.
     */
    public static Event getEvent(int id) {
        for (Event event : Event.values()) {
            if (event.getId() == id) {
                return event;
            }
        }
        throw new IllegalArgumentException("No event found with id: " + id);
    }
}
