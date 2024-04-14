package Files;

import java.io.File;
import java.io.FileWriter;
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
            DataHandler.loadData();
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
            FileWriter writer = new FileWriter(getFile());
            writer.write(DataHandler.saveData());
            writer.close();
            System.out.println("Hotel data succefuly saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getFile() {
        return file;
    }
}
