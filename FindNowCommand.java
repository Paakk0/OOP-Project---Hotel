package Commands;

import Model.Hotel;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a command to find rooms available for immediate occupancy.
 */
public class FindNowCommand extends Command {

    /**
     * Executes the command to find rooms available for immediate occupancy.
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

            // Print number of rooms available for immediate occupancy
            System.out.println("\nNumber of rooms that are possibly available for a switch: " + Hotel.findNow(beds, dateFrom, dateTo));
        } else {
            System.out.println("This command requires 3 arguments!");
        }
    }
}
