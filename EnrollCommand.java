package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

/**
 * Represents a command to enroll a room in an event.
 */
public class EnrollCommand extends Command {

    /**
     * Executes the command to enroll a room in an event.
     *
     * @param args List of arguments passed to the command. Requires 2 arguments: room number and event ID.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            // Parse arguments
            int number = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(Integer.parseInt(args.get(1)));

            // Add event to room
            Hotel.getRooms().get(Hotel.findRoom(number)).addEvent(event);
            System.out.println("Event " + event + " successfully added to room " + number + "!");
        } else {
            System.out.println("This command requires 2 arguments!");
        }
    }
}
