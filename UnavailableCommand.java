package Commands;

import Model.Hotel;

import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a command to mark a room as unavailable for a specified duration.
 */
public class UnavailableCommand extends Command {

    /**
     * Executes the command to mark a room as unavailable for a specified duration.
     *
     * @param args The arguments for the command. It should contain the room number,
     *             start date, end date, and a note.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 4) {
            Hotel.unavailable(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)),
                    convertDateToString(args.get(2)), args.get(3));
        } else {
            System.out.println("This command requires 4 arguments!");
        }
    }
}
