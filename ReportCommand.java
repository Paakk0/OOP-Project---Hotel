package Commands;

import Model.Hotel;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a command to generate a report on room usage within a specified period.
 */
public class ReportCommand extends Command {
    /**
     * Executes the command to generate a report on room usage within a specified period.
     *
     * @param args List of arguments passed to the command.
     *             The first argument should be the start date of the period.
     *             The second argument should be the end date of the period.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            // Parse the start and end dates from the arguments and generate the report
            try {
                Hotel.report(convertDateToString(args.get(0)), convertDateToString(args.get(1)));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use the format: YYYY-MM-DD");
            }
        } else {
            System.out.println("This command requires 2 arguments (start date and end date)!");
        }
    }
}
