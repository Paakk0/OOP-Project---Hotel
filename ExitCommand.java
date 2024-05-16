package Commands;

import java.util.List;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the program.
     *
     * @param args List of arguments passed to the command. No arguments are expected.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            // Exit the program
            System.exit(0);
        } else {
            System.out.println("This command requires no arguments!");
        }
    }
}
