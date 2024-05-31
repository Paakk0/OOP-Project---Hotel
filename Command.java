package Commands;

import UI.ColorCode;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public abstract class Command {
    public abstract void Command(List<String> args);

    protected LocalDate convertStringToDate(String str) {
        try {
            if ("null".equals(str)) {
                return null;
            } else {
                return LocalDate.parse(str);
            }
        } catch (DateTimeParseException e) {
            System.out.println(ColorCode.ERROR.getCode() + "Incorrect date format(YYYY-MM-DD)");
        }
        return null;
    }
}
