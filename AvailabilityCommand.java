package Commands;

import Model.Hotel;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a command to check the availability of rooms on a specific date.
 */
public class AvailabilityCommand extends Command {

    /**
     * Executes the command to check the availability of rooms on a specific date.
     *
     * @param args List of arguments passed to the command. The list should contain:
     *             - The date to check availability (format: yyyy-MM-dd).
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            Hotel.availability(convertDateToString(args.get(0)));
        } else {
            System.out.println("This command requires 1 argument!");
        }
    }
}
