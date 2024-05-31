package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

public class CheckInCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3 && args.get(0).equals("!")) {
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                Hotel.checkIn(Hotel.getRooms().get(i).getNumber(), convertStringToDate(args.get(1)), convertStringToDate(args.get(2)), "TEST", "TestFamily0");
            }
            return;
        }
        if (args.size() == 4 || args.size() == 5) {
            if (args.size() == 4) {
                args.add("0");
            }
            Hotel.checkIn(Integer.parseInt(args.get(0)), convertStringToDate(args.get(1)), convertStringToDate(args.get(2)), args.get(3), args.get(4));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 5 arguments!");
        }
    }
}
