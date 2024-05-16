package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

public class UnrollCommand extends Command{
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            Event e = Event.getEvent(Integer.parseInt(args.get(1)));
            int number = Integer.parseInt(args.get(0));
            Hotel.getRooms().get(Hotel.findRoom(number)).removeEvent(e);
            System.out.println("Event " + e + " successfuly removed from room " + number + "!");
        } else System.out.println("This command requires 2 arguments!");
    }
}
