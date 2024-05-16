package Commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Represents an abstract command.
 */
public abstract class Command {

    /**
     * Executes the command with the given arguments.
     *
     * @param args The arguments passed to the command.
     */
    public abstract void Command(List<String> args);

    /**
     * Converts a string representation of a date to a LocalDate object.
     *
     * @param str The string representation of the date.
     * @return The LocalDate object parsed from the string, or null if the string is "null" or cannot be parsed.
     */
    protected LocalDate convertDateToString(String str) {
        try {
            if ("null".equals(str)) {
                return null;
            } else {
                return LocalDate.parse(str);
            }
        } catch (DateTimeParseException e) {
            // Handle parsing exceptions
        }
        return null;
    }
}
