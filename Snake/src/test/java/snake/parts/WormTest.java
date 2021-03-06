/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.parts;

import snake.parts.Worm;
import snake.parts.Piece;
import snake.parts.Direction;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hallssus
 */
public class WormTest {

    public WormTest() {
    }

    Worm worm;

    @Before
    public void setUp() {
        worm = new Worm(3, 0, Direction.RIGHT, "");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void sizeIsThreeAfterMoving() {
        worm.move();
        worm.move();
        worm.move();
        worm.move();
        assertTrue(worm.getLength() == 3);
    }

    @Test
    public void locationInTheBeginningIsRight() {
        assertTrue(worm.getHeadX() == 3 && worm.getHeadY() == 0);
    }

    @Test
    public void hitsAPieceWorks() {
        Piece piece = new Piece(5, 0);
        worm.move(); //(4,0)
        worm.move(); //(5,0)
        boolean isHit = worm.hitsAPiece(piece);
        assertTrue(isHit == true);
    }

    @Test
    public void hitsItselfReturnsTrueIfHit(){
        worm.setDirection(Direction.RIGHT);
        worm.grow();
        worm.move();
        worm.grow();
        worm.move();
        worm.grow();
        worm.move();
        worm.grow();
        worm.setDirection(Direction.DOWN);
        worm.move();
        worm.setDirection(Direction.LEFT);
        worm.move();
        worm.setDirection(Direction.UP);
        worm.move();
        boolean hit = worm.hitsItself();
        assertTrue(hit == true);
    }
    
    @Test
    public void getLengthWorks(){
        worm.move();
        worm.move();
        worm.move();
        worm.move();
        worm.grow();
        worm.move();
        System.out.println(worm.getLength());
        assertTrue(worm.getLength() == 4);
    }
    
    @Test
    public void setDirectionWorksInAllDirections(){
        worm.setDirection(Direction.LEFT);
        assertTrue(worm.getDirection() != Direction.LEFT);
        worm.setDirection(Direction.DOWN);
        worm.setDirection(Direction.UP);
        assertTrue(worm.getDirection() != Direction.UP);
        worm.setDirection(Direction.RIGHT);
        worm.setDirection(Direction.LEFT);
        assertTrue(worm.getDirection() != Direction.LEFT);
        worm.setDirection(Direction.UP);
        worm.setDirection(Direction.DOWN);
        assertTrue(worm.getDirection() != Direction.DOWN); 
        worm.setDirection(Direction.LEFT);
        worm.setDirection(Direction.RIGHT);
        assertTrue(worm.getDirection() != Direction.RIGHT);
    }
    
    @Test
    public void hitsAWormWorks(){
        Worm wormie = new Worm(3, 0, Direction.LEFT, "testplayer");
        assertTrue(worm.hitsAWorm(wormie));
        assertFalse(worm.hitsAWorm(new Worm(1, 1, Direction.LEFT, "testplayer2")));
    }
    
    @Test
    public void hitsUpOrDownWallWorks(){
        assertTrue(worm.hitsUpOrDownWall(0));
    }
    
    @Test
    public void getIsDeadWorks(){
        assertFalse(worm.getIsDead());
    }
}
