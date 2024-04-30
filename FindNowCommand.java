package Commands;

import Model.Hotel;

import java.time.format.DateTimeParseException;
import java.util.List;

public class FindNowCommand extends Command {
    @Override
    public void Command(List<String> args) {
            if (args.size() == 3) {
                System.out.println("\nNumber of rooms that are possibly available for a switch: " + Hotel.findNow(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2))));
            } else System.out.println("This command requires 3 argument!");
    }
}
