package za.co.wethinkcode;

public class Pawn {

    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    public int getValue() {
        return 1;
    }

    public String getColor() {
        return color;
    }
}