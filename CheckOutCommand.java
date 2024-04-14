package Commands;

import Model.Hotel;

import java.util.List;

public class CheckOutCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            Hotel.checkOut(Integer.parseInt(args.get(0)));
        } else System.out.println("This command requires 1 argument!");
    }
}
