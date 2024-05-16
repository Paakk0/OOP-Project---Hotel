package Commands;

import Files.FileManager;

import java.util.List;

/**
 * Represents a command to open a file.
 */
public class OpenCommand extends Command {

    /**
     * Executes the command to open a file.
     *
     * @param args List of arguments passed to the command.
     *             The single argument should be the name of the file to open.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            String fileName = args.get(0);
            // Attempt to open the specified file
            if (FileManager.openFile(fileName)) {
                System.out.println("File " + FileManager.getFile().getName() + " successfully opened!");
            } else {
                System.out.println("You need to close the currently opened file!");
            }
        } else {
            System.out.println("This command requires 1 argument (file name)!");
        }
    }
}
