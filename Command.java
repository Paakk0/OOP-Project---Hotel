import java.util.Arrays;
import java.util.List;

public class Command {
    private List<String> command;

    private Command(String commandLine) {
        this.command = Arrays.stream(commandLine.split(" ")).toList();
    }

    public static Command getInstance(String commandLine) {
        return new Command(commandLine);
    }

    public List<String> getCommand() {
        return this.command;
    }
}
