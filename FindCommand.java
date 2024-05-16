package Commands;

import Model.Hotel;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command to find available rooms within a specified time frame.
 */
public class FindCommand extends Command {

    /**
     * Executes the command to find available rooms within a specified time frame.
     *
     * @param args List of arguments passed to the command. Requires three arguments: number of beds, start date, and end date.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            // Parse arguments
            int beds = Integer.parseInt(args.get(0));
            LocalDate dateFrom = convertDateToString(args.get(1));
            LocalDate dateTo = convertDateToString(args.get(2));

            // Print number of available rooms
            System.out.println("\nNumber of rooms that are available: " + Hotel.find(beds, dateFrom, dateTo));
        } else {
            System.out.println("This command requires 3 arguments!");
        }
    }
}
