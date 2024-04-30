package Commands;

import Model.Hotel;

import java.util.List;

public class CheckOutCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            if (args.get(0).equals("!")) {
                for (int i = 0; i < Hotel.getRooms().size(); i++) {
                    Hotel.checkOut(Hotel.getRooms().get(i).getNumber());
                }
                return;
            }
            int num = Hotel.checkOut(Integer.parseInt(args.get(0)));
            if (num == -2) {
                System.out.println("This room is already available!");
            } else if (num != -1) {
                System.out.println("Room " + num + " has been checked out!");
            }
        } else System.out.println("This command requires 1 argument!");
    }
}
