package Commands;

import Model.Event;
import Model.Hotel;

import java.util.List;

public class ShowEventsCommand extends Command{
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            for (Event e : Event.values()){
                System.out.println(e+" - "+e.getId());
            }
        } else System.out.println("This command does not require any arguments!");
    }
}
