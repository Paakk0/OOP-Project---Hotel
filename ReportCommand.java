package Commands;

import Model.Hotel;

import java.util.List;

public class ReportCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            Hotel.report(convertDateToString(args.get(0)), convertDateToString(args.get(1)));
        } else System.out.println("This command requires 2 argument!");
    }
}
