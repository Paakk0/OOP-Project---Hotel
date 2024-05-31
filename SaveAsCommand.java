package Commands;

import Files.FileManager;
import UI.ColorCode;

import java.util.List;

public class SaveAsCommand extends Command {
    @Override
    public void Command(List<String> args) {
        if (args.size() != 1) {
            System.out.println(ColorCode.ERROR.getCode() +"This command requires exactly 1 argument (the filename)!");
            return;
        }

        if (FileManager.getFile() != null) {
            String currentFile = FileManager.getFile().getName();
            FileManager.closeFile();
            FileManager.openFile(args.get(0));
            FileManager.save();
            FileManager.closeFile();
            FileManager.openFile(currentFile);
        } else {
            FileManager.openFile(args.get(0));
            FileManager.save();
            FileManager.closeFile();
        }
    }
}
