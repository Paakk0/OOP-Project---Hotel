package Commands;

public class HelpCommand extends Command {

    @Override
    public void Command() {
        System.out.println(
                "The following commands are supported:\n" +
                        "open <file>    \topens <file>\n" +
                        "close          \tcloses currently opened file\n" +
                        "save           \tsaves the currently open file\n" +
                        "saveas <file>  \tsaves the currently open file in <file>\n" +
                        "help           \tprints this information\n" +
                        "exit           \texists the program\n" +
                        "\n" +
                        "checkin <room> <from> <to> <note> <guests> \tRoom becomes unavailable for specified duration\n" +
                        "checkout <room>                            \tRoom becomes available\n" +
                        "find <beds> <from> <to>                    \tShows which rooms are available at chosen period with more or equal beds\n" +
                        "find! <beds> <from> <to>                   \tShows room for important guest, if hotel is full some guests are offered room swap\n" +
                        "availability <date>                        \tShows which rooms are available on that date\n" +
                        "unavailable <room> <from> <to> <note>      \tRoom becomes temporarily unavailable\n" +
                        "report <from> <to>                         \tShows how many days the rooms from a period have been used\n");
    }
}
