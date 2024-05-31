package Commands;

import Files.FileManager;
import UI.ColorCode;

import java.util.List;

public class CloseCommand extends Command {

    @Override
    public void Command(List<String> args) {
        if (args.size() == 0) {
            if (FileManager.closeFile()) {
                System.out.println(ColorCode.DONE.getCode() +"File successfully closed!");
                args.add("!");
                new CheckOutCommand().Command(args);
            } else {
                System.out.println(ColorCode.ERROR.getCode() +"No opened file.");
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() +"This command requires no arguments!");
        }
    }
}
