package Model;

import UI.ColorCode;

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

    Event(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Event getEvent(int id) {
        for (Event event : Event.values()) {
            if (event.getId() == id) {
                return event;
            }
        }
        System.out.println(ColorCode.ERROR.getCode() + "No event found with id: " + id);
        return null;
    }
}
