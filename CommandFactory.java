package Commands;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents a factory for creating commands based on input strings.
 */
public class CommandFactory {
    private static final Map<String, Function<List<String>, Command>> commandMap = new HashMap<>();

    // Initialize the command map with command names and corresponding functions to create commands
    static {
        commandMap.put("open", args -> new OpenCommand());
        commandMap.put("exit", args -> new ExitCommand());
        commandMap.put("help", args -> new HelpCommand());
        commandMap.put("close", args -> new CloseCommand());
        commandMap.put("save", args -> new SaveCommand());
        commandMap.put("saveas", args -> new SaveAsCommand());
        commandMap.put("checkin", args -> new CheckInCommand());
        commandMap.put("availability", args -> new AvailabilityCommand());
        commandMap.put("checkout", args -> new CheckOutCommand());
        commandMap.put("report", args -> new ReportCommand());
        commandMap.put("find", args -> new FindCommand());
        commandMap.put("find!", args -> new FindNowCommand());
        commandMap.put("unavailable", args -> new UnavailableCommand());
        commandMap.put("enroll", args -> new EnrollCommand());
        commandMap.put("unroll", args -> new UnrollCommand());
        commandMap.put("showEvents", args -> new ShowEventsCommand());
        commandMap.put("printEnrolledRooms", args -> new PrintEnrolledRoomsCommand());
    }

    /**
     * Executes the command based on the given input string.
     *
     * @param input The input string representing the command.
     */
    public static void executeCommand(String input) {
        List<String> commandParts = List.of(input.split(" "));
        String commandName = commandParts.get(0);
        List<String> args = commandParts.size() > 1 ? new ArrayList<>(commandParts.subList(1, commandParts.size())) : new ArrayList<>();

        // Get the function to create the command based on the command name
        Function<List<String>, Command> functionList = commandMap.get(commandName);
        if (functionList != null) {
            // Create the command and execute it
            Command command = functionList.apply(args);
            command.Command(args);
        } else {
            System.out.println("Unknown Command: " + commandName);
        }
    }
}
