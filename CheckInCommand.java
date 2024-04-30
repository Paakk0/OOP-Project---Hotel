package Commands;

import Model.Hotel;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class CheckInCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1 && args.get(0).equals("!")) {
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                Hotel.checkIn(Hotel.getRooms().get(i).getNumber(), convertDateToString("2012-04-20"), convertDateToString("2012-04-30"), "TEST", 0);
            }
            return;
        }
        if (args.size() == 4 || args.size() == 5) {
            if (args.size() == 4) {
                args.add("0");
            }
            Hotel.checkIn(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2)), args.get(3), Integer.parseInt(args.get(4)));
        } else System.out.println("This command requires 5 arguments!");
    }
}
