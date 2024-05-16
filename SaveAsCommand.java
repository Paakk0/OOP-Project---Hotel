package Commands;

import Files.FileManager;

import java.util.List;

/**
 * Represents a command to save the current data to a new file or overwrite an existing file.
 */
public class SaveAsCommand extends Command {
    /**
     * Executes the command to save the current data to a new file or overwrite an existing file.
     *
     * @param args List of arguments passed to the command.
     *             The first argument should be the filename for the new file.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() != 1) {
            System.out.println("This command requires exactly 1 argument (the filename)!");
            return;
        }

        if (FileManager.getFile() != null) {
            // If a file is already open, temporarily close it and save the data to the new file
            String currentFile = FileManager.getFile().getName();
            FileManager.closeFile();
            FileManager.openFile(args.get(0));
            FileManager.save();
            FileManager.closeFile();

            // Reopen the previous file
            FileManager.openFile(currentFile);
        } else {
            // If no file is open, simply save the data to the new file
            FileManager.openFile(args.get(0));
            FileManager.save();
            FileManager.closeFile();
        }
    }
}
