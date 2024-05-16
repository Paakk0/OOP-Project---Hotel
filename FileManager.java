package Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The FileManager class provides functionality for file management.
 */
public class FileManager {
    private static File file;

    /**
     * Creates a new file with the specified name.
     *
     * @param fileName The name of the file to create.
     */
    public static void createNewFile(String fileName) {
        if (!file.exists()) {
            try {
                file = new File(fileName);
                System.out.println("Creating file " + fileName + "..");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("You need to close the currently opened file!");
    }

    /**
     * Opens a file with the specified name.
     *
     * @param fileName The name of the file to open.
     * @return true if the file was successfully opened, false otherwise.
     */
    public static boolean openFile(String fileName) {
        if (file == null) {
            if (!fileName.contains(".xml")) {
                fileName += ".xml";
            }
            file = new File(fileName);
            if (!file.exists()) {
                createNewFile(fileName);
            }
            DataHandler.loadData();
            return true;
        }
        return false;
    }

    /**
     * Closes the currently opened file.
     *
     * @return true if the file was successfully closed, false if no file was opened.
     */
    public static boolean closeFile() {
        if (file == null) {
            return false;
        }
        file = null;
        return true;
    }

    /**
     * Saves data to the currently opened file.
     */
    public static void save() {
        if (file != null) {
            try {
                FileWriter writer = new FileWriter(getFile());
                writer.write(DataHandler.saveData());
                writer.close();
                System.out.println("Hotel data successfully saved!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else System.out.println("There is no opened file to save!");
    }

    /**
     * Retrieves the currently opened file.
     *
     * @return The currently opened file.
     */
    public static File getFile() {
        return file;
    }
}
