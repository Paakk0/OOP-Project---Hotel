package Commands;

import Model.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents a command to check in guests to a room.
 */
public class CheckInCommand extends Command {

    /**
     * Executes the command to check in guests to a room.
     *
     * @param args List of arguments passed to the command. The list should contain:
     *             - The room number to check in.
     *             - The check-in date (format: yyyy-MM-dd).
     *             - The check-out date (format: yyyy-MM-dd).
     *             - A note (optional).
     *             - The number of guests (optional, defaults to 0).
     *             If the argument list contains only "!," it will check in guests to all rooms with test data.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1 && args.get(0).equals("!")) {
            // Check in guests to all rooms with test data
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                Hotel.checkIn(Hotel.getRooms().get(i).getNumber(), convertDateToString("2012-04-20"), convertDateToString("2012-04-30"), "TEST", 0);
            }
            return;
        }
        if (args.size() == 4 || args.size() == 5) {
            if (args.size() == 4) {
                args.add("0"); // Default number of guests to 0 if not provided
            }
            Hotel.checkIn(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2)), args.get(3), Integer.parseInt(args.get(4)));
        } else {
            System.out.println("This command requires 5 arguments!");
        }
    }
}
