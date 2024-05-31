package Commands;

import Model.Event;
import Model.Hotel;
import UI.ColorCode;

import java.util.List;

public class ShowEventsCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            for (Event e : Event.values()) {
                System.out.println(ColorCode.SUGGEST.getCode() +e + " - " + e.getId());
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() +"This command does not require any arguments!");
        }
    }
}
