package Commands;

import Files.FileManager;
import UI.ColorCode;

import java.util.List;

public class OpenCommand extends Command {

    @Override
    public void Command(List<String> args) {
        if (args.size() == 1) {
            String fileName = args.get(0);
            if (FileManager.openFile(fileName)) {
                System.out.println(ColorCode.DONE.getCode() + "File " + FileManager.getFile().getName() + " successfully opened!");
            } else {
                System.out.println(ColorCode.ERROR.getCode() + "You need to close the currently opened file!");
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires 1 argument!");
        }
    }
}
