package Commands;

import Model.Hotel;

import java.util.List;

/**
 * Represents a command to check out a room.
 */
public class CheckOutCommand extends Command {

    /**
     * Executes the command to check out a room.
     *
     * @param args List of arguments passed to the command. The list should contain the room number to check out.
     *             If the argument list contains only "!," it will check out all rooms.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            if (args.get(0).equals("!")) {
                // Check out all rooms
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
        } else {
            System.out.println("This command requires 1 argument!");
        }
    }
}
