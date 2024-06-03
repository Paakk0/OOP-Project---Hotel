package Commands;

import Files.FileManager;
import Model.Hotel;
import UI.ColorCode;

import java.util.List;

public class CloseCommand extends Command {

    @Override
    public void Command(List<String> args) {
        if (args.isEmpty()) {
            if (FileManager.closeFile()) {
                System.out.println(ColorCode.DONE.getCode() + "File successfully closed!");
                Hotel.generateRooms();
            } else {
                System.out.println(ColorCode.ERROR.getCode() + "No opened file.");
            }
        } else {
            System.out.println(ColorCode.ERROR.getCode() + "This command requires no arguments!");
        }
    }
}
