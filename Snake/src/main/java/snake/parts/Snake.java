package snake.parts;

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

    /**
     * Contructor to the Snake-game, that sets the apple and the worm(s) into a
     * correct place and direction.
     *
     * @param height The height of the gameboard
     * @param width The width of the gameboard
     * @param numberofworms The number of worms, one or two.
     * @param name1 The name of the player one
     * @param name2 The name of the player two
     */
    public Snake(int height, int width, int numberofworms, String name1, String name2) {

        this.height = height;
        this.width = width;
        this.numberOfWorms = numberofworms;
        worms = new ArrayList<>();
        if (this.numberOfWorms > 1) {
            worm = new Worm(width / 2, height / 3 * 2, Direction.RIGHT, name1);
            worm2 = new Worm(width / 2, height / 3, Direction.LEFT, name2);
            worms.add(worm);
            worms.add(worm2);
        } else {
            worm = new Worm(width / 2, height / 2, Direction.RIGHT, name1);
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

    /**
     * If there is only one worm in the game, returns that.
     *
     * @return Worm2
     */
    public Worm getWorm2() {
        if (worms.size() > 1) {
            worm2.getPlayername();
            return worm2;
        } else {
            return worm;
        }
    }

    /**
     * Puts an apple to a random place, if the apple hits the worm, then it's
     * not okay and a new place is being randomly selected.
     */
    public void setNewApple() {
        int x = 0;
        int y = 0;
        boolean hits = true;
        while (hits) {
            x = new Random().nextInt(width);
            y = new Random().nextInt(height);
            //System.out.println("x: " + x);
            //System.out.println("y: " + y);
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

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public boolean getIsOn() {
        return isOn;
    }

    /**
     * Updates the game: If a worm dies, the game will then stop and if the worm
     * doesn't die, at the end of the method, the worm moves.
     */
    public void update() {
        if (this.worms.size() > 1) {
            if (this.worms.get(0).hitsAWorm(this.worms.get(1))) {
                this.isOn = false;
            }
        }
        for (Worm wormie : this.worms) {

            if (wormie.hitsItself()) {
                wormie.setIsDead(true);
                this.isOn = false;

            } else if (wormie.hitsALeftOrRightWall(-1)) {
                wormie.setIsDead(true);
                this.isOn = false;

            } else if (wormie.hitsALeftOrRightWall(width)) {
                wormie.setIsDead(true);
                this.isOn = false;

            } else if (wormie.hitsUpOrDownWall(-1)) {
                wormie.setIsDead(true);
                this.isOn = false;

            } else if (wormie.hitsUpOrDownWall(height)) {
                wormie.setIsDead(true);
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

    public Apple getApple() {
        return this.apple;
    }

    public int getWorm1length() {
        return this.worm.getLength();
    }

    /**
     * If there is only one worm at the game, the length of that worm will be
     * returned.
     *
     * @return length of the worm2
     */
    public int getWorm2length() {

        if (this.worms.size() > 1) {
            return this.worm2.getLength();
        } else {
            return 0;
        }
    }

//muuta myöhemmin sellaseksi, että jos madot kuolevat samaan aikaan, molemmat häviävät tms
    /**
     * In order to find put the loser, we need to know which worm has died.
     *
     * @return The worm that died.
     */
    public Worm getLoser() {
        for (Worm wormie : this.worms) {
            if (wormie.getIsIsDead() == true) {
                return wormie;
            }
        }
        return this.worm;
    }
}
