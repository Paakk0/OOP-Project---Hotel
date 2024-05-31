package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

public class AvailabilityCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            Hotel.availability(convertStringToDate(args.get(0)));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument!");
        }
    }
}
