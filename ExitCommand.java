package Commands;

import UI.ColorCode;

import java.util.List;

public class ExitCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            System.exit(0);
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires no arguments!");
        }
    }
}
