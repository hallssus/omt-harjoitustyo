package snake.parts;

import java.util.ArrayList;

public class Worm {

    private ArrayList<Piece> pieces;
    private int startX; //coordinates for the head
    private int startY;
    private int length;
    private Direction direction;
    private boolean isDead;
    private String playername;
    private int speed;
    private int boostcounter;

    /**
     * The constructor for the worm
     *
     * @param x Starting position's x-coordinate
     * @param y Starting position's y-coordinate
     * @param initdir Initial direction for the worm.
     * @param player The player's name.
     */
    public Worm(int x, int y, Direction initdir, String player) {
        this.boostcounter = 0;
        this.speed = 1;
        this.playername = player;
        this.isDead = false;
        this.startX = x;
        this.startY = y;
        this.direction = initdir;
        this.length = 3;
        pieces = new ArrayList<>();
        Piece piece = new Piece(this.startX, this.startY);
        pieces.add(piece);

    }

    public int getHeadX() {
        return startX;
    }

    public String getPlayername() {
        return playername;
    }

    public int getHeadY() {
        return startY;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return this.length;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setHeadX(int headX) {
        this.startX = headX;
    }

    public void setHeadY(int headY) {
        this.startY = headY;
    }

    /**
     * Sets the direction. It cannot be the opposite direction.
     *
     * @param direction The new wanted direction.
     */
    public void setDirection(Direction direction) {
        if (this.direction == Direction.DOWN && direction == Direction.UP) {
            return;
        } else if (this.direction == Direction.LEFT && direction == Direction.RIGHT) {
            return;
        } else if (this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        } else if (this.direction == Direction.UP && direction == Direction.DOWN) {
            return;
        } else {
            this.direction = direction;
        }
    }

    /**
     * Moves the worm in to the direction it has.
     */
    public void move() {
        int wherex = 0, wherey = 0;
        if (this.direction == Direction.DOWN) {
            wherey = 1;
        } else if (this.direction == Direction.LEFT) {
            wherex = -1;
        } else if (this.direction == Direction.RIGHT) {
            wherex = 1;
        } else if (this.direction == Direction.UP) {
            wherey = -1;
        }
        pieces.add(new Piece(pieces.get(pieces.size() - 1).getX() + wherex, pieces.get(pieces.size() - 1).getY() + wherey)); //adds a new block to the end of the list to give illution of movement
        if (this.length < pieces.size()) { //we'll need this later because eating causes lenght to increase.
            if (this.pieces.size() > 3) { //so that the first three moves make the worm as big as it is.
                pieces.remove(0); //lets delete the tail
            }
        }
    }

    /**
     * A method that checks if the worm has hit itself and therefore id dead.
     *
     * @return True if the worm hits itself.
     */
    public boolean hitsItself() {
        for (int i = 0; i < this.pieces.size(); i++) {
            for (int j = 0; j < this.pieces.size(); j++) {
                int x = this.pieces.get(i).getX();
                int y = this.pieces.get(i).getY();
                if (j != i && x == this.pieces.get(j).getX() && y == this.pieces.get(j).getY()) {
                    this.isDead = true;
                    return true;
                }
            }
        }
        return false;
    }

    public void grow() {
        this.length++;
    }

    /**
     * To check if the worm hits another piece (an apple, other worm)
     *
     * @param piece The piece that might be hit.
     * @return True if the worm hits the piece.
     */
    public boolean hitsAPiece(Piece piece) {

        for (Piece part : this.pieces) {

            if (part.getX() == piece.getX() && part.getY() == piece.getY()) {

                return true;
            }
        }
        return false;
    }

    /**
     * Check if the worm has hit a wall and therefore died.
     *
     * @param x Declares if it's a left (x = 0) or right (x = width - 1) wall
     * that is checked.
     * @return True if the worm has hit a wall.
     */
    public boolean hitsALeftOrRightWall(int x) {
        for (Piece part : this.pieces) {
            if (part.getX() == x) {
                this.isDead = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the worm has hit a wall and therefore died.
     *
     * @param y Declares if it's a up (y = 0) or down (y = height - 1) wall that
     * is checked.
     * @return True if the worm has hit a wall.
     */
    public boolean hitsUpOrDownWall(int y) {
        for (Piece part : this.pieces) {
            if (part.getY() == y) {
                this.isDead = true;
                return true;
            }
        }
        return false;

    }

    /**
     * Checks if the worm has hit a piece in an other worm.
     *
     * @param worm That might be hit
     * @return True if the worm hits another worm.
     */
    public boolean hitsAWorm(Worm worm) {
        for (Piece piece : this.pieces) {
            for (Piece piece2 : worm.getPieces()) {
                if (piece.getX() == piece2.getX() && piece.getY() == piece2.getY()) {
                    this.isDead = true;
                    return true;
                }
            }

        }
        return false;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBoostcounter() {
        return boostcounter;
    }

    public void setBoostcounter(int boostcounter) {
        this.boostcounter = boostcounter;
    }

}
