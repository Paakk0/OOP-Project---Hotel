package Commands;

import Files.FileManager;

import java.util.List;

public class SaveCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            FileManager.save();
        }
        else System.out.println("This command does not require any arguments!");
    }
}
