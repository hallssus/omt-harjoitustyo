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
    private int time;
    private ArrayList<Worm> worms;
    private Boost boost;

    /**
     * Constructor to the Snake-game for one player
     *
     * @param height The height of the board
     * @param width The width of the board
     * @param name1 The name of the player one
     */
    public Snake(int height, int width, String name1) {
        this.time = 0;
        this.height = height;
        this.width = width;
        this.numberOfWorms = 1;
        worms = new ArrayList<>();
        worm = new Worm(width / 2, height / 2, Direction.RIGHT, name1);
        worms.add(worm);
        setNewApple();
        this.isOn = true;

    }

    /**
     * A constructor for a duel.
     *
     * @param height The height of the board
     * @param width The width of the board
     * @param name1 The name of the player one
     * @param name2 The name of the player two
     */
    public Snake(int height, int width, String name1, String name2) {
        this.time = 0;
        this.height = height;
        this.width = width;
        this.numberOfWorms = 2;
        worms = new ArrayList<>();
        worm = new Worm(width / 2, height / 3 * 2, Direction.RIGHT, name1);
        worm2 = new Worm(width / 2, height / 3, Direction.LEFT, name2);
        worms.add(worm);
        worms.add(worm2);
        setNewApple();
        setNewBoost();
        this.isOn = true;
    }

    public int getNumberOfWorms() {
        return numberOfWorms;
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
        int x = 0, y = 0;
        boolean hits = true;
        while (hits) {
            x = new Random().nextInt(width);
            y = new Random().nextInt(height);
            this.apple = new Apple(x, y);
            if (this.numberOfWorms < 2) {
                if (!worm.hitsAPiece(apple)) {
                    hits = false;
                }
            } else {
                if (!this.worm2.hitsAPiece(apple) && !this.worm.hitsAPiece(apple)) {
                    hits = false;
                }
            }
        }
        setApple(apple);
    }

    /**
     * Sets new boost on the board into a random place.
     */
    public void setNewBoost() {
        int x = 0, y = 0;
        boolean hits = true;
        while (hits) {
            x = new Random().nextInt(width);
            y = new Random().nextInt(height);
            this.boost = new Boost(x, y);

            if (!this.worm2.hitsAPiece(this.boost) && !this.worm.hitsAPiece(this.boost)) {
                hits = false;
                break;
            }
        }
        this.boost.setX(x);
        this.boost.setY(y);
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    public boolean getIsOn() {
        return isOn;
    }

    /**
     * Updates the game: If a worm dies, the game will then stop and if the worm
     * doesn't die, at the end of the method, the worm moves.
     */
    public void update(Worm wormie) {
        this.time++;
        if (this.worms.size() > 1) {
            if (this.worms.get(0).hitsAWorm(this.worms.get(1))) {
                this.isOn = false;
            }
        }
        int oldspeed = 1;
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
            wormie.setSpeed(wormie.getSpeed() + 1);
            setNewApple();
        }
        if (this.numberOfWorms > 1) {
            boost(wormie, oldspeed);
        }
        if (this.isOn) {
            wormie.move();
        }
    }

    /**
     * If a boost is eaten, the speed increases for a while.
     *
     * @param wormie The worm that's speed increases
     * @param oldspeed The old speed of the worm
     */
    public void boost(Worm wormie, int oldspeed) {
        if (wormie.hitsAPiece(boost)) {
            oldspeed = wormie.getSpeed();
            wormie.setBoostcounter(20);
            setNewBoost();
        }
        if (wormie.getBoostcounter() > 0) {
            wormie.setSpeed(8);
            wormie.setBoostcounter(wormie.getBoostcounter() - 1);
        } else {
            wormie.setSpeed(oldspeed);
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

    /**
     * In order to find put the loser, we need to know which worm has died.
     *
     * @return The worm that died.
     */
    public Worm getLoser() {
        for (Worm wormie : this.worms) {
            if (wormie.getIsDead() == true) {
                return wormie;
            }
        }
        return this.worm;
    }

    public Boost getBoost() {
        return boost;
    }
    
    public int getTime(){
        return this.time;
    }
    
    public void setBoost(Boost boost){
        this.boost = boost;
    }

}
