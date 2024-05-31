package UI;

public enum ColorCode {
    ERROR("\u001B[31m"),
    NORMAL("\u001B[36m"),
    DONE("\u001B[32m"),
    SUGGEST("\u001B[33m"),
    CLEAR("\u001B[0m");

    private final String value;

    ColorCode(String value) {
        this.value = value;
    }

    public String getCode() {
        return this.value;
    }
}
