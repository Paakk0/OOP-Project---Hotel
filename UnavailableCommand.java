package Commands;

import Model.Hotel;

import java.util.List;

public class UnavailableCommand extends Command {

    @Override
    public void Command(List<String> args) {
        if (args.size() == 4) {
            Hotel.unavailable(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2)), args.get(3));
        } else System.out.println("This command requires 4 argument!");
    }
}
