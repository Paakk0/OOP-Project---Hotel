package Model;

public class Guest {
    private String identity;
    private int number;

    public Guest(String identity, int number) {
        this.identity = identity;
        this.number = number;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
