package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

public class PrintEnrolledRoomsCommand extends Command{
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            Event e = Event.getEvent(Integer.parseInt(args.get(0)));
            for (int i = 0;i<Hotel.getRooms().size();i++){
                if (Hotel.getRooms().get(i).getEvents().contains(e)){
                    System.out.println("Room "+Hotel.getRooms().get(i).getNumber());
                }
            }
        } else System.out.println("This command requires 1 arguments!");
    }
}
