/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

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
        worm = new Worm(8, 0, Direction.RIGHT);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sizeIsThreeAfterMoving(){
        worm.move();
        worm.move();
        worm.move();
        worm.move();
        assertTrue(worm.getLength() == 3);
    }
    
    

//    @Test
//    public void hitsItselfReturnsTrueIfHit(){
//        worm.move();
//        worm.setDirection(Direction.DOWN);
//        worm.move();
//        worm.setDirection(Direction.LEFT);
//        worm.move();
//        worm.setDirection(Direction.UP);
//        worm.move();
//        boolean hit = worm.hitsItself();
//        assertTrue(hit == true);
//    }
}
