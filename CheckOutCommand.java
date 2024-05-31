package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;


public class CheckOutCommand extends Command {

    @Override
    public void Command(List<String> args) {
        if (args.size() == 1 || args.size() == 2) {
            if (args.size() == 1) {
                args.add("-1");
            }
            if (args.get(0).equals("!")) {
                for (int i = 0; i < Hotel.getRooms().size(); i++) {
                    Hotel.checkOut(Hotel.getRooms().get(i).getNumber(), Integer.parseInt(args.get(1)));
                }
                return;
            }
            Hotel.checkOut(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument!");
        }
    }
}
