package Commands;

import UI.ColorCode;

public enum CommandName {
    OPEN("open"),
    EXIT("exit"),
    HELP("help"),
    CLOSE("close"),
    SAVE("save"),
    SAVEAS("saveas"),
    CHECKIN("checkin"),
    AVAILABILITY("availability"),
    CHECKOUT("checkout"),
    REPORT("report"),
    FIND("find"),
    FINDi("find!"),
    UNAVAILABLE("unavailable"),
    ENROLL("enroll"),
    UNROLL("unroll"),
    SHOWEVENTS("showEvents"),
    PRINTENROLLEDROOMS("printEnrolledRooms");

    private final String id;

    CommandName(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static CommandName getValue(String val) {
        for (CommandName com : CommandName.values()) {
            if (com.getId().equals(val)) return com;
        }
        System.out.println(ColorCode.ERROR.getCode() +"Unknown command: " + val);
        return null;
    }

}
