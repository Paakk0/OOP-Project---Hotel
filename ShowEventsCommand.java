package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

/**
 * Represents a command to show all available events.
 */
public class ShowEventsCommand extends Command {
    /**
     * Executes the command to show all available events.
     *
     * @param args List of arguments passed to the command (not used).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            // Display all available events along with their IDs
            for (Event e : Event.values()) {
                System.out.println(e + " - " + e.getId());
            }
        } else {
            System.out.println("This command does not require any arguments!");
        }
    }
}
