package parts;

import java.util.*;

public class Snake {

    private int height;
    private int width;
    private boolean isOn;
    private Worm worm;
    private Worm worm2;
    private Apple apple;
    private int numberOfWorms;

    private ArrayList<Worm> worms;

    public Snake(int height, int width, int numberofworms) {

        this.height = height;
        this.width = width;
        this.numberOfWorms = numberofworms;
        if (this.numberOfWorms > 1) {
            worm = new Worm(width / 2, height / 3 * 2, Direction.RIGHT);
            worm2 = new Worm(width / 2, height / 3, Direction.LEFT);
            worms = new ArrayList<>();
            worms.add(worm);
            worms.add(worm2);
        } else {
            worm = new Worm(width / 2, height / 2, Direction.RIGHT);
            worms = new ArrayList<>();
            worms.add(worm);
        }
        boolean applePlaceNotOk = true;
        while (applePlaceNotOk) { //if it hits the worm its not ok
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);

            apple = new Apple(x, y);

            if (!worm.hitsAPiece(apple)) {
                applePlaceNotOk = false;
            }
            this.isOn = true;

        }
    }

    public int getNumberOfWorms() {
        return numberOfWorms;
    }

    public String getAppleCoordinates() {
        return apple.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Worm getWorm() {
        return worm;
    }

    public Worm getWorm2() {
        return worm2;
    }

//puts an apple to a random place
    public void setNewApple() {
        int x = 0;
        int y = 0;
        boolean hits = true;
        while (hits) {
            x = new Random().nextInt(width);
            y = new Random().nextInt(height);
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            if (!worm.hitsAPiece(new Piece(x, y))) {
                hits = false;
                break;
            }
        }
        this.apple.setX(x);
        this.apple.setY(y);
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public boolean getIsOn() {
        return isOn;
    }

    public void update() {
        if (this.worms.size() > 1) {
            if (this.worms.get(0).hitsAWorm(this.worms.get(1))) {
                this.isOn = false;
            }
        }
        for (Worm wormie : this.worms) {

            if (wormie.hitsItself()) {
                this.isOn = false;

            } else if (wormie.hitsALeftOrRightWall(-1)) {
                this.isOn = false;

            } else if (wormie.hitsALeftOrRightWall(width)) {
                this.isOn = false;

            } else if (wormie.hitsUpOrDownWall(-1)) {
                this.isOn = false;

            } else if (wormie.hitsUpOrDownWall(height)) {
                this.isOn = false;

            } else if (wormie.hitsAPiece(apple)) {
                wormie.grow();
                setNewApple();
            }
            if (this.isOn) {
                wormie.move();
            }
        }
    }

    @Override
    public String toString() {
        String text = "Place of the worm:" + worm.getPieces().get(worm.getPieces().size() - 1) + ". Place of the apple: " + getAppleCoordinates();
        return text;
    }

    public Apple getApple() {
        return this.apple;
    }

}
