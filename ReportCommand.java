package Commands;

import Model.Hotel;
import UI.ColorCode;

import java.util.List;

public class ReportCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 2) {
            Hotel.report(convertStringToDate(args.get(0)), convertStringToDate(args.get(1)));
        } else {
            System.out.println(ColorCode.ERROR.getCode() +"This command requires 2 arguments!");
        }
    }
}
