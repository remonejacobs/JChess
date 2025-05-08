package za.co.wethinkcode;

public abstract class Piece {

    private final String color;

    public Piece(String color) {
        this.color = color;
    }

    public int getValue() {
        return 1;
    }

    public String getColor() {
        return color;
    }

}
