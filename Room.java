package Model;

import java.time.LocalDate;

public class Room {
    private int number, numberOfBeds, numberOfGuests;
    private boolean available;
    private LocalDate dateFrom, dateTo;
    private String note;

    public Room(int number, int numberOfBeds, int numberOfGuests, boolean available, LocalDate dateFrom, LocalDate to, String note) {
        this.number = number;
        this.numberOfBeds = numberOfBeds;
        this.numberOfGuests = numberOfGuests;
        this.available = available;
        this.dateFrom = dateFrom;
        this.dateTo = to;
        this.note = note;
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

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        if (this.available) {
            this.dateFrom = null;
            this.dateTo = null;
        }
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}