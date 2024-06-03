package Commands;

import Model.Event;
import Model.Guest;
import Model.Hotel;
import Model.Reservation;
import UI.ColorCode;

import java.util.List;

public class PrintEnrolledRoomsCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            int eventId = Integer.parseInt(args.get(0));
            Event event = Event.getEvent(eventId);
            for (int i = 0; i < Hotel.getRooms().size(); i++) {
                for (Reservation r : Hotel.getRooms().get(i).getReservations()) {
                    for (Guest g : r.getGuests()) {
                        if (g.getEvents().contains(event)) {
                            System.out.println(ColorCode.DONE.getCode() + "Room " + Hotel.getRooms().get(i).getNumber());
                        }
                    }
                }
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument (event ID)!");
        }
    }
}
