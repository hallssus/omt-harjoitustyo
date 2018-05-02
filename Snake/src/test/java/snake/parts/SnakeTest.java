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
        snake = new Snake(10, 10, 1, "", "");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void amountOfWormsWorks() {
        snake = new Snake(10, 10, 2, "", "");
        assertTrue(snake.getNumberOfWorms() == 2);
    }

    @Test
    public void updateWorks() {
        snake.update();
        assertTrue(snake.getIsOn() == true);
    }
    
    @Test
    public void updateWorksIfHitsRightWall(){
        snake.update();
        snake.update();
        snake.update();
        snake.update();
        snake.update();
        snake.update();
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
        snake = new Snake(10, 10, 2, "name1", "name2");
        assertTrue(snake.getWorm2length() == 3);

    }

}
