package Model;

import UI.ColorCode;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class Hotel {
    private static List<Room> rooms;

    public Hotel() {
        rooms = new LinkedList<>();
    }

    public static void generateRooms() {
        int beds = 5;
        for (int i = 1; i <= 400; i = i + 100) {
            for (int j = 0; j <= 30; j++) {
                if (i == 1) {
                    j = 1;
                    i = 0;
                }
                rooms.add(new Room(i + j, beds, null));
            }
            beds--;
        }
    }

    public static void unavailable(int room, LocalDate dateFrom, LocalDate dateTo, String note) {
        checkIn(room, dateFrom, dateTo, note, "Staff0");
    }

    public static void availability(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
            System.out.println(ColorCode.SUGGEST.getCode() + "Using today's date for this search!");
        }
        Room r;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            if (r.checkIfAvailable(date, date)) {
                System.out.println(ColorCode.DONE.getCode() + "Room " + r.getNumber() + " is available at " + date);
            }
        }
    }

    public static void report(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            System.out.println(ColorCode.ERROR.getCode() + "Dates cannot be null!");
            return;
        }
        boolean overallDataFound = false;

        for (Room r : rooms) {
            boolean dataFound = false;
            int days = 0;
            for (Reservation res : r.getReservations()) {
                if (!(dateTo.isBefore(res.getFrom()) || dateFrom.isAfter(res.getTo()))) {
                    days += res.getTo().compareTo(res.getFrom());
                    dataFound = true;
                    overallDataFound = true;
                }
            }
            if (dataFound) {
                System.out.println(ColorCode.DONE.getCode() + "Room " + r.getNumber() + " is being used for " + days + " days in the specified period.");
            }
        }

        if (!overallDataFound) {
            System.out.println(ColorCode.ERROR.getCode() + "No data of room use at that period!");
        }
    }

    public static void checkIn(int number, LocalDate dateFrom, LocalDate dateTo, String note, String identity) {
        int index = findRoom(number);
        if (index != -1 && getRooms().get(index).checkIfAvailable(dateFrom, dateTo)) {
            if (dateFrom.isBefore(LocalDate.now()) || dateTo.isBefore(LocalDate.now())) {
                System.out.println(ColorCode.SUGGEST.getCode() + "Going back in time?\n" + ColorCode.ERROR.getCode() + "Reservations cannot be made for dates in the past!");
                return;
            }
            int guests = intParser(identity);
            if (rooms.get(index).getNumberOfBeds() >= guests) {
                rooms.get(index).setNote(note);
                if (guests == 0) {
                    guests = rooms.get(index).getNumberOfBeds();
                }
                rooms.get(index).addReservation(new Reservation(dateFrom, dateTo, identity.substring(0, identity.length() - 1), guests));
                System.out.println(ColorCode.DONE.getCode() + "Room " + rooms.get(index).getNumber() + " has been checked in!");
            } else {
                System.out.println(ColorCode.ERROR.getCode() + "This room only has " + rooms.get(index).getNumberOfBeds());
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This rooms is unavailable!");
        }
    }

    private static int intParser(String value) {
        char ch = value.charAt(value.length() - 1);
        try {
            return Integer.parseInt(String.valueOf(ch));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int findRoom(int number) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getNumber() == number) {
                return i;
            }
        }
        System.out.println(ColorCode.ERROR.getCode() + "There is no such room!");
        return -1;
    }

    public static int find(int beds, LocalDate dateFrom, LocalDate dateTo) {
        if (beds >= 1 && beds <= 5) {
            Room r;
            int count = 0;
            for (int i = 0; i < getRooms().size(); i++) {
                r = getRooms().get(i);
                if ((r.getNumberOfBeds() >= beds) && r.checkIfAvailable(dateFrom, dateTo)) {
                    System.out.println(ColorCode.DONE.getCode() + "Room number " + r.getNumber() + " is available!");
                    count++;
                }
            }
            return count;
        }
        System.out.println(ColorCode.ERROR.getCode() + "The hotel contains rooms with 1-5 beds!");
        return -1;
    }

    public static Integer[][][] findNow(int beds, LocalDate dateFrom, LocalDate dateTo) {
        List<Room> potentialRooms = new LinkedList<>();
        Room r;
        Reservation res;
        for (int i = 0; i < rooms.size(); i++) {
            r = rooms.get(i);
            res = getReservedPeriodIndex(r, dateFrom, dateTo);
            assert res != null;
            if (beds <= r.getNumberOfBeds() - calcGuests(res)) {
                potentialRooms.add(r);
            }
        }
        return roomSwitchAdvisor(potentialRooms, beds, dateFrom, dateTo);
    }

    private static Reservation getReservedPeriodIndex(Room room, LocalDate from, LocalDate to) {
        for (Reservation res : room.getReservations()) {
            if (from.isBefore(res.getTo()) && to.isAfter(res.getFrom())) {
                return res;
            }
        }
        return null;
    }

    private static int calcGuests(Reservation reservation) {
        int sum = 0;
        for (Guest g : reservation.getGuests()) {
            sum += g.getNumber();
        }
        return sum;
    }

    private static Integer[][][] roomSwitchAdvisor(List<Room> potentialRooms, int beds, LocalDate from, LocalDate to) {
        if (!potentialRooms.isEmpty()) {
            Integer[][][] indexLink = new Integer[potentialRooms.size()][rooms.size()][2];
            Reservation resM, resN;
            int m, n, counter;
            for (int i = 0; i < indexLink.length; i++) {
                m = findRoom(potentialRooms.get(i).getNumber());
                counter = 0;
                for (int j = 0; j < rooms.size(); j++) {
                    n = j;
                    resM = getReservedPeriodIndex(rooms.get(m), from, to);
                    resN = getReservedPeriodIndex(rooms.get(n), from, to);
                    if (rooms.get(m).getNumber() != rooms.get(n).getNumber() &&
                            (rooms.get(m).getNumberOfBeds() - calcGuests(resM)) >= calcGuests(resN) &&
                            rooms.get(n).getNumberOfBeds() >= beds) {
                        indexLink[i][counter][0] = m;
                        indexLink[i][counter][1] = n;
                        counter++;
                    }
                }
            }
            if (indexLink.length != 0) {
                for (int i = 0; i < indexLink.length; i++) {
                    for (int k = 0; k < indexLink[i].length; k++) {
                        if (indexLink[i][k][0] != null) {
                            System.out.println(ColorCode.NORMAL.getCode() + "Room " + rooms.get(indexLink[i][k][0]).getNumber() + " (" + calcGuests(getReservedPeriodIndex(rooms.get(indexLink[i][k][0]), from, to)) + " | " + rooms.get(indexLink[i][k][0]).getNumberOfBeds() + " guests) <- Room " + rooms.get(indexLink[i][k][1]).getNumber() + " (" + calcGuests(getReservedPeriodIndex(rooms.get(indexLink[i][k][1]), from, to)) + " | " + rooms.get(indexLink[i][k][1]).getNumberOfBeds() + " guests)");
                        }
                    }
                }
                return indexLink;
            }
        }
        System.out.println(ColorCode.ERROR.getCode() + "Hotel is completely full!");
        return null;
    }

    public static void moveGuests(Integer[][][] indexLink, LocalDate from, LocalDate to) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ColorCode.NORMAL.getCode() + "Do you want move the guests to another room?\n" +
                ColorCode.DONE.getCode() + "Yes" + ColorCode.CLEAR.getCode() + " or " + ColorCode.ERROR.getCode() + "No" +
                ColorCode.NORMAL.getCode() + "\n");
        System.out.print(">");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            System.out.println(ColorCode.NORMAL.getCode() + "Select the room you want to move other guests to:");
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < indexLink.length; i++) {
                for (int j = 0; j < indexLink[i].length; j++) {
                    if (indexLink[i][j][0] != null) {
                        if (!temp.contains(indexLink[i][j][0])) {
                            temp.add(indexLink[i][j][0]);
                            System.out.print(rooms.get(indexLink[i][j][0]).getNumber() + ", ");
                        }
                    }
                }
            }
            System.out.print("\n>");
            answer = scanner.nextLine();
            if (temp.contains(findRoom(Integer.parseInt(answer)))) {
                int receiverId = findRoom(Integer.parseInt(answer));
                System.out.println(ColorCode.NORMAL.getCode() + "Select the room that will be cleared:");
                temp.clear();
                for (int i = 0; i < indexLink.length; i++) {
                    for (int j = 0; j < indexLink[i].length; j++) {
                        if (indexLink[i][j][0] != null) {
                            temp.add(indexLink[i][j][1]);
                            System.out.print(rooms.get(indexLink[i][j][1]).getNumber() + ", ");
                        }
                    }
                }//find! 2 2024-10-10 2024-10-20
                System.out.print("\n>");
                answer = scanner.nextLine();
                if (temp.contains(findRoom(Integer.parseInt(answer)))) {
                    int senderId = findRoom(Integer.parseInt(answer));
                    Reservation receiver = getReservedPeriodIndex(rooms.get(receiverId), from, to);
                    Reservation sender = getReservedPeriodIndex(rooms.get(senderId), from, to);
                    assert sender != null;
                    assert receiver != null;
                    receiver.getGuests().addAll(sender.getGuests());
                    rooms.get(senderId).removeReservation(sender);
                    System.out.println(ColorCode.DONE.getCode() + "Room swap complete!");
                }
                return;
            }
            System.out.println(ColorCode.ERROR.getCode() + "This room isn't listed");
            return;
        }
        System.out.println(ColorCode.NORMAL.getCode() + "No changes have been made!");
    }

    public static void checkOut(int number, Integer resId) {
        int index = findRoom(number);
        int id = resId - 1;
        if (index != -1) {
            if (!rooms.get(index).getReservations().isEmpty()) {
                if (resId == -1) {
                    rooms.get(index).getReservations().clear();
                    System.out.println(ColorCode.DONE.getCode() + "Room " + rooms.get(index).getNumber() + " has been checked out!");
                    return;
                } else if (rooms.get(index).getReservations().size() >= resId) {
                    rooms.get(index).getReservations().remove(id);
                    System.out.println(ColorCode.DONE.getCode() + "Room " + rooms.get(index).getNumber() + " has been checked out!");
                    return;
                }
                System.out.println(ColorCode.ERROR.getCode() + "Reservation doesn't exist!");
                return;
            }
            System.out.println(ColorCode.ERROR.getCode() + "This room is already available!");
            return;
        }
        System.out.println(ColorCode.ERROR.getCode() + "Room number " + number + " doesn't exist!");
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    public static void setRooms(List<Room> rs) {
        rooms = rs;
    }
}
