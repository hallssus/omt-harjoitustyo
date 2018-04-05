package parts;

public class Apple extends Piece {

    public Apple(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "(" + super.getX() + "," + super.getY() + ")";
    }

}
