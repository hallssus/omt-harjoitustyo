package parts;

import java.util.*;

public class Snake {

    private int height;
    private int width;
    private boolean isOn;
    private Worm worm;
    private Apple apple;

    public Snake(int height, int width) {
        worm = new Worm(width / 2, height / 2, Direction.RIGHT);
        this.height = height;
        this.width = width;
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
        if (this.isOn) {
            this.worm.move();
        }

        if (worm.hitsItself()) {
            this.isOn = false;

        } else if (worm.hitsALeftOrRightWall(0)) {
            this.isOn = false;

        } else if (worm.hitsALeftOrRightWall(width)) {
            this.isOn = false;

        } else if (worm.hitsUpOrDownWall(0)) {
            this.isOn = false;

        } else if (worm.hitsUpOrDownWall(height)) {
            this.isOn = false;

        } else if (worm.hitsAPiece(apple)) {
            worm.grow();
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
