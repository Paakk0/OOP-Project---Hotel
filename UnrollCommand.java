package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

/**
 * Represents a command to unroll an event from a room.
 */
public class UnrollCommand extends Command {
    /**
     * Executes the command to unroll an event from a room.
     *
     * @param args The arguments for the command.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            // Parse arguments
            int number = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(Integer.parseInt(args.get(1)));

            // Remove event from room
            Hotel.getRooms().get(Hotel.findRoom(number)).removeEvent(event);
            System.out.println("Event " + event + " successfully removed from room " + number + "!");
        } else {
            System.out.println("This command requires 2 arguments!");
        }
    }
}
