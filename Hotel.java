package Model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * Represents a hotel with rooms and functionalities.
 */
public class Hotel {
    private static List<Room> rooms;

    /**
     * Default constructor for the Hotel class.
     * Initializes the list of rooms.
     */
    public Hotel() {
        rooms = new LinkedList<>();
    }

    /**
     * Generates rooms for the hotel.
     * Rooms are added to the list based on certain criteria.
     */
    public static void generateRooms() {
        Random random = new Random();
        for (int i = 1; i <= 400; i = i + 100) {
            for (int j = 0; j <= 30; j++) {
                if (i == 1) {
                    j = 1;
                    i = 0;
                }
                rooms.add(new Room(i + j, random.nextInt(1, 6), 0, true, null, null, null));
            }
        }
    }

    /**
     * Marks a room as unavailable for a specific period.
     *
     * @param room     The room number to mark as unavailable.
     * @param dateFrom The start date of unavailability.
     * @param dateTo   The end date of unavailability.
     * @param note     Additional notes regarding the unavailability.
     */
    public static void unavailable(int room, LocalDate dateFrom, LocalDate dateTo, String note) {
        checkIn(room, dateFrom, dateTo, note, 0);
    }

    /**
     * Checks the availability of rooms on a specific date.
     *
     * @param date The date for which availability needs to be checked.
     */
    public static void availability(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        Room r;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            if (r.isAvailable() || (r.getDateFrom().compareTo(date) >= 1 || r.getDateTo().compareTo(date) <= -1)) {
                System.out.println("Room " + r.getNumber() + " is available at " + date);
            }
        }
    }

    /**
     * Generates a report of room usage within a specified period.
     *
     * @param dateFrom The start date of the period.
     * @param dateTo   The end date of the period.
     */
    public static void report(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            System.out.println("Dates cannot be null!");
        }
        Room r;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            if (r.getDateFrom() != null && r.getDateTo() != null &&
                    (dateFrom.compareTo(r.getDateFrom()) >= 0 || dateTo.compareTo(r.getDateTo()) <= 0)) {
                System.out.println("Room " + r.getNumber() + " is being used for " + r.getDateTo().compareTo(r.getDateFrom()));
                return;
            }
        }
        System.out.println("No data of room use at that period!");
    }

    /**
     * Checks a guest into a room for a specific period.
     *
     * @param number  The room number.
     * @param dateFrom The start date of the stay.
     * @param dateTo   The end date of the stay.
     * @param note     Additional notes regarding the stay.
     * @param guests   The number of guests staying in the room.
     */
    public static void checkIn(int number, LocalDate dateFrom, LocalDate dateTo, String note, Integer guests) {
        int index = findRoom(number);
        if (index != -1 && rooms.get(index).isAvailable()) {
            if (rooms.get(index).getNumberOfBeds() >= guests) {
                rooms.get(index).setAvailable(false);
                rooms.get(index).setDateFrom(dateFrom);
                rooms.get(index).setDateTo(dateTo);
                rooms.get(index).setNote(note);
                if (guests == 0) {
                    rooms.get(index).setNumberOfGuests(rooms.get(index).getNumberOfBeds());
                } else rooms.get(index).setNumberOfGuests(guests);
                System.out.println("Room " + rooms.get(index).getNumber() + " has been checked in!");
            } else {
                System.out.println("This room only has " + rooms.get(index).getNumberOfBeds());
            }
        } else {
            System.out.println("This rooms is unavailable!");
        }
    }

    /**
     * Finds the index of a room in the list based on its number.
     *
     * @param number The room number to find.
     * @return The index of the room in the list, or -1 if not found.
     */
    public static int findRoom(int number) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber() == number) {
                return i;
            }
        }
        System.out.println("There is no such room!");
        return -1;
    }

    /**
     * Finds available rooms based on specified criteria.
     *
     * @param beds     The number of beds required.
     * @param dateFrom The start date of the period.
     * @param dateTo   The end date of the period.
     * @return The number of available rooms.
     */
    public static int find(int beds, LocalDate dateFrom, LocalDate dateTo) {
        if (beds >= 1 && beds <= 5) {
            Room r;
            int count = 0;
            for (int i = 0; i < getRooms().size(); i++) {
                r = getRooms().get(i);
                if ((r.getNumberOfBeds() >= beds) && (r.isAvailable() || (dateFrom.isAfter(r.getDateTo()) || dateTo.isBefore(r.getDateFrom())) && (dateFrom != r.getDateFrom() && dateTo != r.getDateTo()))) {
                    System.out.println("Room number " + r.getNumber() + " is available!");
                    count++;
                }
            }
            return count;
        }
        System.out.println("The hotel contains rooms with 1-5 beds!");
        return -1;
    }

    /**
     * Finds available rooms for the current date or a specified period.
     *
     * @param beds     The number of beds required.
     * @param dateFrom The start date of the period.
     * @param dateTo   The end date of the period.
     * @return The number of available rooms.
     */
    public static int findNow(int beds, LocalDate dateFrom, LocalDate dateTo) {
        int count = find(beds, dateFrom, dateTo);
        if (count > 0)
            return count;
        else if (count != -1) {
            List<Room> potentialRooms = new LinkedList<>();
            Room r;
            for (int i = 0; i < rooms.size(); i++) {
                r = rooms.get(i);
                if (r.getNumberOfBeds() > r.getNumberOfGuests()) {
                    potentialRooms.add(r);
                }
            }
            return roomSwitchAdvisor(potentialRooms, beds);
        }
        return -1;
    }

    /**
     * Advises on room switching options based on availability and bed requirements.
     *
     * @param temp The list of potential rooms.
     * @param beds The number of beds required.
     * @return The number of available rooms.
     */
    private static int roomSwitchAdvisor(List<Room> temp, int beds) {
        Integer[][][] indexLink = new Integer[temp.size()][rooms.size()][2];
        int m, n, counter;
        for (int i = 0; i < indexLink.length; i++) {
            m = findRoom(temp.get(i).getNumber());
            counter = 0;
            for (int j = 0; j < rooms.size(); j++) {
                n = j;
                if (rooms.get(m).getNumber() != rooms.get(n).getNumber() &&
                        (rooms.get(m).getNumberOfBeds() - rooms.get(m).getNumberOfGuests()) >= rooms.get(n).getNumberOfGuests() &&
                        rooms.get(n).getNumberOfBeds() >= beds) {
                    indexLink[i][counter][0] = m;
                    indexLink[i][counter][1] = n;
                    counter++;
                }
            }
        }
        System.out.println(indexLink[0][0][1]);
        if (indexLink.length != 0) {
            for (int i = 0; i < indexLink.length; i++) {
                for (int k = 0; k < indexLink[i].length; k++) {
                    if (indexLink[i][k][0] != null) {
                        System.out.println("Room " + rooms.get(indexLink[i][k][0]).getNumber() + " (" + rooms.get(indexLink[i][k][0]).getNumberOfGuests() + " | " + rooms.get(indexLink[i][k][0]).getNumberOfBeds() + " guests) <- Room " + rooms.get(indexLink[i][k][1]).getNumber() + " (" + rooms.get(indexLink[i][k][1]).getNumberOfGuests() + " | " + rooms.get(indexLink[i][k][1]).getNumberOfBeds() + " guests)");
                    }
                }
            }
            return indexLink.length;
        }
        System.out.println("Hotel is completely full!");
        return 0;
    }

    /**
     * Finds the next available index in a multi-dimensional array.
     *
     * @param arr The multi-dimensional array.
     * @return The next available index.
     */
    private static int nextEl(Integer[][] arr) {
        int i;
        for (i = 0; i < arr.length; i++) {
            if (arr[i][0] == null) {
                break;
            }
            ;
        }
        return i;
    }

    /**
     * Checks out a guest from a room.
     *
     * @param number The room number to check out from.
     * @return The room number checked out from, or -1 if the room was not found, or -2 if the room is already available.
     */
    public static int checkOut(int number) {
        int index = findRoom(number);
        if (index != -1) {
            if (!rooms.get(index).isAvailable()) {
                rooms.get(index).setAvailable(true);
                return rooms.get(index).getNumber();
            }
            return -2;
        }
        return -1;
    }

    /**
     * Retrieves the list of rooms.
     *
     * @return The list of rooms.
     */
    public static List<Room> getRooms() {
        return rooms;
    }

    /**
     * Sets the list of rooms.
     *
     * @param rs The list of rooms to set.
     */
    public static void setRooms(List<Room> rs) {
        rooms = rs;
    }
}
