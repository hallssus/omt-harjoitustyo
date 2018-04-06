package parts;

import java.util.ArrayList;

public class Worm {

    private ArrayList<Piece> pieces;
    private int startX; //coordinates for the head
    private int startY;
    private int length;
    private Direction direction;

    public Worm(int x, int y, Direction initdir) {
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

    public int getHeadY() {
        return startY;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getLength() {
        return pieces.size();
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPieces(ArrayList<Piece> newpieces) {
        this.pieces = newpieces;
    }

    public void move() {
        int wherex = 0;
        int wherey = 0;

        if (this.direction == Direction.DOWN) {
            wherex = 0;
            wherey = 1;
        } else if (this.direction == Direction.LEFT) {
            wherex = -1;
            wherey = 0;
        } else if (this.direction == Direction.RIGHT) {
            wherex = 1;
            wherey = 0;
        } else if (this.direction == Direction.UP) {
            wherex = 0;
            wherey = -1;
        }

        int newx = pieces.get(pieces.size() - 1).getX() + wherex;
        int newy = pieces.get(pieces.size() - 1).getY() + wherey;
        
        pieces.add(new Piece(newx, newy)); //adds a new block to the end of the list to give illution of movement
        if (this.length < pieces.size()) { //we'll need this later because eating causes lenght to increase.
            if (this.pieces.size() > 3) { //so that the first three moves make the worm as big as it is.
                pieces.remove(0); //lets delete the tail
            }
        }

    }

    public boolean hitsItself() { //returns true if the worm hits itself
        for (int i = 0; i < this.pieces.size(); i++) {
            for (int j = 0; j < this.pieces.size(); j++) {
                int x = this.pieces.get(i).getX();
                int y = this.pieces.get(i).getY();
                if (j != i && x == this.pieces.get(j).getX() && y == this.pieces.get(j).getY()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void grow(){
        this.length++;
    }
    
    public boolean hitsAPiece(Piece piece){
        
        for (Piece part : this.pieces){
            
            if (part.getX() == piece.getX() && part.getY() == piece.getY()){
                
                return true;
            }
        }
        return false;
    }
    
    public boolean hitsALeftOrRightWall(int x){
        for (Piece part : this.pieces){
            if (part.getX() == x){
                return true;
            }
        }
        return false;
    }
    
    public boolean hitsUpOrDownWall(int y){
        for (Piece part : this.pieces){
            if (part.getY() == y){
                return true;
            }
        }
        return false;
    }

}
