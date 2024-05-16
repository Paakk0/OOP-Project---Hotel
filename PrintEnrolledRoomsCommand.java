package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

/**
 * Represents a command to print rooms enrolled in a specific event.
 */
public class PrintEnrolledRoomsCommand extends Command {
    /**
     * Executes the command to print rooms enrolled in a specific event.
     *
     * @param args List of arguments passed to the command.
     *             The single argument should be the ID of the event.
     */
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            // Parse the event ID from the argument
            int eventId = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(eventId);

            // Print rooms enrolled in the specified event
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                if (Hotel.getRooms().get(i).getEvents().contains(event)) {
                    System.out.println("Room " + Hotel.getRooms().get(i).getNumber());
                }
            }
        } else {
            System.out.println("This command requires 1 argument (event ID)!");
        }
    }
}
