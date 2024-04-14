package Commands;

import Model.Hotel;

import java.util.List;

public class FindCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 3) {
            Hotel.find(Integer.parseInt(args.get(0)), convertDateToString(args.get(1)), convertDateToString(args.get(2)));
        } else System.out.println("This command requires 3 argument!");
    }
}
