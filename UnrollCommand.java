package Commands;

import Model.Event;
import Model.Guest;
import Model.Hotel;
import Model.Reservation;
import UI.ColorCode;

import java.util.List;

public class UnrollCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            int number = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(Integer.parseInt(args.get(1)));
            if (event != null) {
                for (Reservation r : Hotel.getRooms().get(Hotel.findRoom(number)).getReservations()) {
                    for (Guest g : r.getGuests()) {
                        if (g.getIdentity().equals(args.get(2))) {
                            g.removeEvent(event);
                            System.out.println(ColorCode.DONE.getCode() + "Event " + event + " successfully removed from room " + number + "!");
                            return;
                        }
                    }
                }
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 3 arguments!");
        }
    }
}
