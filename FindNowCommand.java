package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.time.LocalDate;
import java.util.List;

public class FindNowCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            int beds = Integer.parseInt(args.get(0));
            LocalDate dateFrom = convertStringToDate(args.get(1));
            LocalDate dateTo = convertStringToDate(args.get(2));
            Integer[][][] result = Hotel.findNow(beds, dateFrom, dateTo);
            if (result.length > 0) {
                System.out.println(ColorCode.DONE.getCode() + "\nNumber of rooms that are possibly available for a switch: " + result.length);
                Hotel.moveGuests(result, convertStringToDate(args.get(1)), convertStringToDate(args.get(2)));
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 3 arguments!");
        }
    }
}
