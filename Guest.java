package Model;

import java.util.ArrayList;
import java.util.List;

public class Guest {
    private String identity;
    private int number;
    private List<Event> events;

    public Guest(String identity, int number) {
        this.identity = identity;
        this.number = number;
        this.events = new ArrayList<>();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }

    public void removeEvent(Event e) {
        this.events.remove(e);
    }
}
