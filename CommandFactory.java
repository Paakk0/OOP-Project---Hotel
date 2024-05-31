package Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private static final Map<CommandName, Function<List<String>, Command>> commandMap = new HashMap<>();

    static {
        commandMap.put(CommandName.OPEN, args -> new OpenCommand());
        commandMap.put(CommandName.EXIT, args -> new ExitCommand());
        commandMap.put(CommandName.HELP, args -> new HelpCommand());
        commandMap.put(CommandName.CLOSE, args -> new CloseCommand());
        commandMap.put(CommandName.SAVE, args -> new SaveCommand());
        commandMap.put(CommandName.SAVEAS, args -> new SaveAsCommand());
        commandMap.put(CommandName.CHECKIN, args -> new CheckInCommand());
        commandMap.put(CommandName.AVAILABILITY, args -> new AvailabilityCommand());
        commandMap.put(CommandName.CHECKOUT, args -> new CheckOutCommand());
        commandMap.put(CommandName.REPORT, args -> new ReportCommand());
        commandMap.put(CommandName.FIND, args -> new FindCommand());
        commandMap.put(CommandName.FINDi, args -> new FindNowCommand());
        commandMap.put(CommandName.UNAVAILABLE, args -> new UnavailableCommand());
        commandMap.put(CommandName.ENROLL, args -> new EnrollCommand());
        commandMap.put(CommandName.UNROLL, args -> new UnrollCommand());
        commandMap.put(CommandName.SHOWEVENTS, args -> new ShowEventsCommand());
        commandMap.put(CommandName.PRINTENROLLEDROOMS, args -> new PrintEnrolledRoomsCommand());

    }
    public static void executeCommand(String input) {
        List<String> commandParts = List.of(input.split(" "));
        CommandName commandName = CommandName.getValue(commandParts.get(0));
        List<String> args = commandParts.size() > 1 ? new ArrayList<>(commandParts.subList(1, commandParts.size())) : new ArrayList<>();

        Function<List<String>, Command> functionList = commandMap.get(commandName);
        if (functionList != null) {
            Command command = functionList.apply(args);
            command.Command(args);
        }
    }
}
