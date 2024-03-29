package Commands;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private static final Map<String, Function<List<String>, Command>> commandMap = new HashMap<>();

    static {
        commandMap.put("open", args -> new OpenCommand());
        commandMap.put("exit", args -> new ExitCommand());
        commandMap.put("help", args -> new HelpCommand());
        commandMap.put("close", args -> new CloseCommand());
        commandMap.put("save", args -> new SaveCommand());
        commandMap.put("saveas", args -> new SaveAsCommand());
    }

    public static void executeCommand(String input) {
        List<String> commandParts = List.of(input.split(" "));
        String commandName = commandParts.get(0);
        List<String> args = commandParts.size() > 1 ? new ArrayList<>(commandParts.subList(1, commandParts.size())) : new ArrayList<>();

        Function<List<String>, Command> functionList = commandMap.get(commandName);
        if (functionList != null) {
            Command command = functionList.apply(args);
            command.Command(args);
        } else System.out.println("Unknown Command: " + commandName);
    }
}
