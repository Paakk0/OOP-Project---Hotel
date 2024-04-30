package Commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public abstract class Command {
    public abstract void Command(List<String> args);

    protected LocalDate convertDateToString(String str) {
        try {
            if ("null".equals(str)) {
                return null;
            } else {
                return LocalDate.parse(str);
            }
        } catch (DateTimeParseException e) {
        }
        return null;
    }
}
