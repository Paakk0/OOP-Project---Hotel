package Commands;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public abstract class Command {
    public abstract void Command(List<String> args);

    protected LocalDate convertDateToString(String str) {
        if ("null".equals(str)) {
            return null;
        } else {
            return LocalDate.parse(str);
        }
    }
}
