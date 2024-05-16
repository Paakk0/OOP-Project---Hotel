package Commands;

import Files.FileManager;

import java.util.List;

/**
 * Represents a command to save the current data to a file.
 */
public class SaveCommand extends Command {
    /**
     * Executes the command to save the current data to a file.
     *
     * @param args List of arguments passed to the command (not used).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            // Save the current data
            FileManager.save();
        } else {
            System.out.println("This command does not require any arguments!");
        }
    }
}
