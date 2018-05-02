/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.parts;

/**
 *
 * @author hallssus
 */
public class Piece {

    private int x;
    private int y;
/**
 * Constructor for the piece that all the worm consist of
 * @param x X-coordinate
 * @param y Y-coordinate
 */
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
/**
 * Checks if the piece hits another piece
 * @param piece The piece that might be hit
 * @return True if the pieces hits each other
 */
    public boolean hitsAnother(Piece piece) {
        if (this.x == piece.getX() && this.y == piece.getY()) {
            return true;
        } else {
            return false;
        }
    }

}
