/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.parts;

import snake.parts.Snake;
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
public class SnakeTest {

    Snake snake;

    public SnakeTest() {
    }

    @Before
    public void setUp() {
        snake = new Snake(10, 10, "name1");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void amountOfWormsWorks() {
        snake = new Snake(10, 10, "", "");
        assertTrue(snake.getNumberOfWorms() == 2);
    }

    @Test
    public void updateWorksIfNotDead() {
        snake.update(snake.getWorm());
        assertTrue(snake.getIsOn() == true);
    }
    
    @Test
    public void updateWorksIfHitsAnApple(){
        Apple apple = new Apple(6,5);
        snake.setApple(apple);
        snake.update(snake.getWorm());
        snake.update(snake.getWorm());
        assertTrue(snake.getWorm().hitsAPiece(apple));
    }

    @Test
    public void updateWorksIfHitsRightWall() {
        snake.update(snake.getWorm());
        snake.update(snake.getWorm());
        snake.update(snake.getWorm());
        snake.update(snake.getWorm());
        snake.update(snake.getWorm());
        snake.update(snake.getWorm());
        assertTrue(snake.getIsOn() == false);
    }
    
    @Test
    public void updateWorksIfHitsUpWall(){
        Worm worm = snake.getWorm();
        worm.setDirection(Direction.UP);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        assertTrue(snake.getIsOn() == false);
    }
    
    @Test
    public void updateWorksIfHitsLeftWall(){
        Worm worm = snake.getWorm();
        worm.setDirection(Direction.DOWN);
        snake.update(worm);
        worm.setDirection(Direction.LEFT);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        assertTrue(snake.getIsOn() == false);
    }
    
    @Test
    public void updateWorksIfHitsDownWall(){
        Worm worm = snake.getWorm();
        worm.setDirection(Direction.DOWN);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        snake.update(worm);
        assertTrue(snake.getIsOn() == false);
    }
    
    @Test
    public void updateWorksIfHitsItself(){
        Worm worm = snake.getWorm();
        worm.setLength(6);
        snake.update(worm);
        worm.setDirection(Direction.DOWN);
        snake.update(worm);
        worm.setDirection(Direction.LEFT);
        snake.update(worm);
        worm.setDirection(Direction.UP);
        snake.update(worm);
        snake.update(worm);
        assertTrue(snake.getIsOn() == false);
    }

    @Test
    public void setNewAppleWorks() {
        snake.setNewApple();
        boolean hits = snake.getWorm().hitsAPiece(snake.getApple());
        assertTrue(hits == false);
    }

    @Test
    public void getWorm2LengthWorksIfOnlyOneWorm() {
        assertTrue(snake.getWorm2length() == 0);
    }

    @Test
    public void getWorm2LengthWorksIfTwoWorms() {
        snake = new Snake(10, 10, "name1", "name2");
        assertTrue(snake.getWorm2length() == 3);

    }

    @Test
    public void getWorm2Works(){
        assertEquals("name1", snake.getWorm2().getPlayername());
        Snake snakey = new Snake(10, 10, "name1", "name2");
        String name2 = snakey.getWorm2().getPlayername();
        assertEquals("name2", name2);
    }
    
    @Test
    public void getLoserWorks(){
        snake.getWorm().setIsDead(true);
        assertTrue(snake.getLoser().equals(snake.getWorm()));
    }
    
    
}
