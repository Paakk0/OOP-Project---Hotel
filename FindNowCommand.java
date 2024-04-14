package Commands;

import Model.Hotel;

import java.util.List;

public class FindNowCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            Hotel.findNow(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2)));
        } else System.out.println("This command requires 3 argument!");
    }
}
