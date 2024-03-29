package Commands;

import java.util.List;

public class ExitCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            System.exit(0);
        } else System.out.println("This command requires no arguments!");
    }
}
