package Commands;

import Files.FileManager;

import java.util.List;

/**
 * Represents a command to close the currently opened file.
 */
public class CloseCommand extends Command {

    /**
     * Executes the command to close the currently opened file.
     *
     * @param args List of arguments passed to the command. Requires no arguments.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            if (FileManager.closeFile()) {
                System.out.println("File successfully closed!");
                // Trigger a check-out command to reset hotel data
                args.add("!");
                new CheckOutCommand().Command(args);
            } else {
                System.out.println("No opened file.");
            }
        } else {
            System.out.println("This command requires no arguments!");
        }
    }
}
