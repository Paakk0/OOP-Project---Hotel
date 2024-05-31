package Commands;

import Model.Event;
import Model.Hotel;
import UI.ColorCode;

import java.util.List;

public class UnrollCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            int number = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(Integer.parseInt(args.get(1)));
            Hotel.getRooms().get(Hotel.findRoom(number)).removeEvent(event);
            System.out.println(ColorCode.DONE.getCode() + "Event " + event + " successfully removed from room " + number + "!");
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 2 arguments!");
        }
    }
}
