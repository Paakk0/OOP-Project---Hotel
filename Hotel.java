package Model;

import Model.Room;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hotel {
    private static Hotel hotel;
    private static List<Room> rooms;

    public Hotel() {
        rooms = new LinkedList<>();
    }

    public static void generateRooms() {
        Random random = new Random();
        for (int i = 1; i <= 400; i = i + 100) {
            for (int j = 0; j <= 30; j++) {
                if (i == 1) {
                    j = 1;
                    i = 0;
                }
                rooms.add(new Room(i + j, random.nextInt(1, 4), 0, true, null, null, null));
            }
        }
    }

    public static void unavailable(int room, LocalDate dateFrom, LocalDate dateTo, String note) {
        checkIn(room, dateFrom, dateTo, note, 0);
    }

    public static void availability(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        Room r;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            if (r.isAvailable() || (r.getDateFrom().compareTo(date) > 0 || r.getDateTo().compareTo(date) < 0)) {
                System.out.println("Room " + r.getNumber() + " is available at " + date);
            }
        }
    }

    public static void report(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            System.out.println("Dates cannot be null!");
        }
        Room r;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            if (r.getDateFrom().compareTo(dateFrom) > 0 || r.getDateTo().compareTo(dateTo) < 0) {
                System.out.println("Room " + r.getNumber() + " is being used for " + (dateTo.getDayOfYear() - dateFrom.getDayOfYear()));
            }
        }
    }

    public static void checkIn(int number, LocalDate dateFrom, LocalDate dateTo, String note, Integer guests) {
        Room room = findRoom(number);
        if (room.isAvailable()) {
            room.setAvailable(false);
            room.setDateFrom(dateFrom);
            room.setDateTo(dateTo);
            room.setNote(note);
            if (guests == null) {
                room.setNumberOfGuests(room.getNumberOfBeds());
            } else room.setNumberOfGuests(guests);
        } else {
            System.out.println("This rooms is currently unavailable!");
        }
    }

    public static int find(int beds, LocalDate dateFrom, LocalDate dateTo) {
        Room r;
        int count = 0;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            if (r.getNumberOfBeds() >= beds && r.isAvailable() || (r.getDateFrom().compareTo(dateFrom) >= 0 || r.getDateTo().compareTo(dateTo) <= 0)) {
                System.out.println("Room number " + r.getNumber() + " is available!");
                count++;
            }
        }
        System.out.println(count + " are available at that time!");
        return count;
    }

    public static int findNow(int beds, LocalDate dateFrom, LocalDate dateTo) {
        int count = find(beds, dateFrom, dateTo);
        if (count > 0)
            return count;
        else {

        }
    }

    public static void checkOut(int number) {//DONE
        findRoom(number).setAvailable(true);
    }

    public static Room findRoom(int number) {

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber() == number) {
                return rooms.get(i);
            }
        }
        return null;
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(List<Room> rooms) {
        rooms = rooms;
    }
}
