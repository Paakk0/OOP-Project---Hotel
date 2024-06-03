package Files;

import Model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public enum XmlStructure {
    HOTEL("Hotel"),
    ROOM("Room"),
    NUMBER("Number"),
    NUMBEROFBEDS("NumberOfBeds"),
    NOTE("Note"),
    EVENTS("Events"),
    EVENT("Event"),
    RESERVATIONS("Reservations"),
    RESERVATION("Reservation"),
    DATEFROM("DateFrom"),
    DATETO("DateTo"),
    GUESTDETAILS("GuestDetails"),
    GUESTS("Guests"),
    IDENTITY("Identity"),
    NUMBEROFGUESTS("NumberOfGuests");

    private final String value;

    XmlStructure(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String createXmlStructure() {
        StringBuilder xml = new StringBuilder();
        xml.append("<").append(HOTEL.getValue()).append(">\n");

        for (Room r : Hotel.getRooms()) {
            xml.append("\t<").append(ROOM.getValue()).append(" ")
                    .append(NUMBER.getValue()).append("=\"").append(r.getNumber()).append("\">\n")
                    .append("\t\t<").append(NUMBEROFBEDS.getValue()).append(">")
                    .append(r.getNumberOfBeds())
                    .append("</").append(NUMBEROFBEDS.getValue()).append(">\n");

            xml.append("\t\t<").append(NOTE.getValue()).append(">")
                    .append(r.getNote())
                    .append("</").append(NOTE.getValue()).append(">\n");

            List<Reservation> reservations = r.getReservations();
            if (!reservations.isEmpty()) {
                xml.append("\t\t<").append(RESERVATIONS.getValue()).append(">\n");
                for (Reservation res : reservations) {
                    xml.append("\t\t\t<").append(RESERVATION.getValue()).append(">\n")
                            .append("\t\t\t\t<").append(DATEFROM.getValue()).append(">")
                            .append(res.getFrom())
                            .append("</").append(DATEFROM.getValue()).append(">\n")
                            .append("\t\t\t\t<").append(DATETO.getValue()).append(">")
                            .append(res.getTo())
                            .append("</").append(DATETO.getValue()).append(">\n");
                    List<Guest> guests = res.getGuests();
                    if (!guests.isEmpty()) {
                        xml.append("\t\t\t\t<").append(GUESTDETAILS.getValue()).append(">\n");
                        for (Guest g : guests) {
                            xml.append("\t\t\t\t\t<").append(GUESTS.getValue()).append(">\n")
                                    .append("\t\t\t\t\t\t<").append(IDENTITY.getValue()).append(">")
                                    .append(g.getIdentity())
                                    .append("</").append(IDENTITY.getValue()).append(">\n")
                                    .append("\t\t\t\t\t\t<").append(NUMBEROFGUESTS.getValue()).append(">")
                                    .append(g.getNumber())
                                    .append("</").append(NUMBEROFGUESTS.getValue()).append(">\n");
                            if (!g.getEvents().isEmpty()) {
                                List<Event> events = g.getEvents();
                                xml.append("\t\t\t\t\t\t<").append(EVENTS.getValue()).append(">\n");
                                for (Event e : events) {
                                    xml.append("\t\t\t\t\t\t\t<").append(EVENT.getValue()).append(">")
                                            .append(e.getId())
                                            .append("</").append(EVENT.getValue()).append(">\n");
                                }
                                xml.append("\t\t\t\t\t\t</").append(EVENTS.getValue()).append(">\n");
                            }
                            xml.append("\t\t\t\t\t</").append(GUESTS.getValue()).append(">\n")
                                    .append("\t\t\t\t</").append(GUESTDETAILS.getValue()).append(">\n");
                        }
                        xml.append("\t\t\t</").append(RESERVATION.getValue()).append(">\n");
                    }
                    xml.append("\t\t</").append(RESERVATIONS.getValue()).append(">\n");
                }
            }

            xml.append("\t</").append(ROOM.getValue()).append(">\n");
        }

        xml.append("</").append(HOTEL.getValue()).append(">");
        return xml.toString();
    }
    //checkin 1 2024-10-10 2024-10-20 note family0

    public static String fetchXmlData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FileManager.getFile()));
        StringBuilder xmlData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            xmlData.append(line);
        }
        reader.close();
        return xmlData.toString();
    }
}
