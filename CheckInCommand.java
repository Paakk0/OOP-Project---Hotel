package Commands;

import Model.Hotel;

import java.time.LocalDate;
import java.util.List;

public class CheckInCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 5) {
            Hotel.checkIn(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2)), args.get(3), Integer.parseInt(args.get(4)));
        }
        else System.out.println("This command requires 5 arguments!");
    }
}
