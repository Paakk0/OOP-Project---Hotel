package Commands;

import Files.FileManager;

import java.util.LinkedList;
import java.util.List;

public class CloseCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            if (FileManager.closeFile()) {
                System.out.println("File succefuly closed!");
                args.add("!");
                new CheckOutCommand().Command(args);
            } else {
                System.out.println("No opened file..");
            }
        } else System.out.println("This command requires no arguments!");
    }
}
