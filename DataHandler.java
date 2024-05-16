package Files;

import Model.Hotel;
import Model.Room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Handles data serialization and deserialization for the hotel rooms.
 */
public class DataHandler {

    /**
     * Converts the current state of the hotel rooms to an XML format.
     *
     * @return The XML representation of the hotel rooms.
     */
    public static String saveData() {
        String result = "<Hotel>\n";
        for (int i = 0; i < Hotel.getRooms().size(); i++) {
            Room r = Hotel.getRooms().get(i);
            result += "" +
                    "\t<Room index='" + i + "'>\n" +
                    "\t\t<Number>" + r.getNumber() + "</Number>\n" +
                    "\t\t<Available>" + r.isAvailable() + "</Available>\n" +
                    "\t\t<NumberOfBeds>" + r.getNumberOfBeds() + "</NumberOfBeds>\n" +
                    "\t\t<NumberOfGuests>" + r.getNumberOfGuests() + "</NumberOfGuests>\n" +
                    "\t\t<DateFrom>" + r.getDateFrom() + "</DateFrom>\n" +
                    "\t\t<DateTo>" + r.getDateTo() + "</DateTo>\n" +
                    "\t\t<Note>" + r.getNote() + "</Note>\n" +
                    "\t</Room>\n";
        }
        result += "</Hotel>";
        return result;
    }

    /**
     * Loads data from an XML file and updates the hotel rooms accordingly.
     */
    public static void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileManager.getFile()));
            String line;
            StringBuilder xmlData = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                xmlData.append(line);
            }
            reader.close();
            convert(xmlData.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts XML data to hotel room objects and updates the state of the hotel.
     *
     * @param data The XML data representing the hotel rooms.
     */
    private static void convert(String data) {
        List<String> contents = List.of(data.split("</Room>"));
        List<Room> newHotel = Hotel.getRooms();
        Room r;
        String line;
        for (int i = 0; i < contents.size() - 1; i++) {
            line = contents.get(i);
            if (extractIndex(line) == i) {
                r = newHotel.get(i);
                r.setNumber(extractNumber(line));
                r.setAvailable(extractAvailable(line));
                r.setNumberOfBeds(extractNumberOfBeds(line));
                r.setNumberOfGuests(extractNumberOfGuests(line));
                r.setDateFrom(extractDateFrom(line));
                r.setDateTo(extractDateTo(line));
                r.setNote(extractNote(line));
            }
        }
        Hotel.setRooms(newHotel);
    }

    /**
     * Extracts the index of a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The index of the room.
     */
    private static int extractIndex(String xmlData) {
        String indexStr = xmlData.substring(xmlData.indexOf("index='") + 7, xmlData.indexOf("'>"));
        return Integer.parseInt(indexStr);
    }

    /**
     * Extracts the room number from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The room number.
     */
    private static int extractNumber(String xmlData) {
        String numberStr = xmlData.substring(xmlData.indexOf("<Number>") + 8, xmlData.indexOf("</Number>"));
        return Integer.parseInt(numberStr);
    }

    /**
     * Extracts the availability status of a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The availability status of the room.
     */
    private static boolean extractAvailable(String xmlData) {
        String availableStr = xmlData.substring(xmlData.indexOf("<Available>") + 11, xmlData.indexOf("</Available>"));
        return Boolean.parseBoolean(availableStr);
    }

    /**
     * Extracts the number of beds in a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The number of beds in the room.
     */
    private static int extractNumberOfBeds(String xmlData) {
        String bedsStr = xmlData.substring(xmlData.indexOf("<NumberOfBeds>") + 14, xmlData.indexOf("</NumberOfBeds>"));
        return Integer.parseInt(bedsStr);
    }

    /**
     * Extracts the number of guests in a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The number of guests in the room.
     */
    private static int extractNumberOfGuests(String xmlData) {
        String guestsStr = xmlData.substring(xmlData.indexOf("<NumberOfGuests>") + 16, xmlData.indexOf("</NumberOfGuests>"));
        return Integer.parseInt(guestsStr);
    }

    /**
     * Extracts the check-in date of a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The check-in date of the room.
     */
    private static LocalDate extractDateFrom(String xmlData) {
        String dateFromStr = xmlData.substring(xmlData.indexOf("<DateFrom>") + 10, xmlData.indexOf("</DateFrom>"));
        if ("null".equals(dateFromStr)) {
            return null;
        } else {
            return LocalDate.parse(dateFromStr);
        }
    }

    /**
     * Extracts the check-out date of a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The check-out date of the room.
     */
    private static LocalDate extractDateTo(String xmlData) {
        String dateToString = xmlData.substring(xmlData.indexOf("<DateTo>") + 8, xmlData.indexOf("</DateTo>"));
        if ("null".equals(dateToString)) {
            return null;
        } else {
            return LocalDate.parse(dateToString);
        }
    }

    /**
     * Extracts the note associated with a room from the XML data.
     *
     * @param xmlData The XML data containing room information.
     * @return The note associated with the room.
     */
    private static String extractNote(String xmlData) {
        return xmlData.substring(xmlData.indexOf("<Note>") + 6, xmlData.indexOf("</Note>"));
    }
}
