package Commands;

import Files.FileManager;

import java.util.List;

public class OpenCommand extends Command {

    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            String fileName = args.get(0);
            if(FileManager.openFile(fileName)){
                System.out.println("File " + FileManager.getFile().getName() + " succesfuly opened!");
            }
            else System.out.println("You need to close the currently opened file!");
        }
        else System.out.println("This command requires 1 argument!");
    }
}
