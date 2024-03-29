package Files;

import Model.Hotel;
import Model.Room;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class FileManager {
    private static File file;

    public static void createNewFile(String fileName) {
        if (!file.exists()) {
            try {
                file = new File(fileName);
                System.out.println("Creating file " + fileName+"..");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("You need to close the currently opened file!");
    }

    public static boolean openFile(String fileName) {
        if (file == null) {
            if (!fileName.contains(".xml")) {
                fileName += ".xml";
            }
            file = new File(fileName);
            if (!file.exists()) {
                createNewFile(fileName);
            }
            return true;
        }
        return false;
    }

    public static boolean closeFile() {
        if (file == null) {
            return false;
        }
        file = null;
        return true;
    }

    public static void save() {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            Element root = doc.createElement("Hotel");
            doc.appendChild(root);
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                Room r = Hotel.getRooms().get(i);
                Element room = doc.createElement("Room");
                room.setAttribute("id", String.valueOf(i));//ID
                room.setAttribute("number", String.valueOf(r.getNumber()));//NUMBER
                room.setAttribute("available", String.valueOf(r.isAvailable()));//AVAILABLE

                Element beds = doc.createElement("numberOfBeds");
                beds.setTextContent(String.valueOf(r.getNumberOfBeds()));//BEDS
                room.appendChild(beds);

                Element guests = doc.createElement("numberOfGuests");
                guests.setTextContent(String.valueOf(r.getNumberOfGuests()));//GUESTS
                room.appendChild(guests);

                Element dateFrom = doc.createElement("dateFrom");
                dateFrom.setTextContent(String.valueOf(r.getDateFrom()));//DATE_FROM
                room.appendChild(dateFrom);

                Element dateTo = doc.createElement("dateTo");
                dateTo.setTextContent(String.valueOf(r.getDateTo()));//DATE_TO
                room.appendChild(dateTo);

                Element note = doc.createElement("note");
                note.setTextContent(String.valueOf(r.getNote()));//NOTE
                room.appendChild(note);
                root.appendChild(room);
            }

            DOMSource dom = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(dom, result);
            System.out.println("Hotel data succefuly saved!");
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getFile() {
        return file;
    }
}
