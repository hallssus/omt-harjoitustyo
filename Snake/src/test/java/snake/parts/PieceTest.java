/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.parts;

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
public class PieceTest {
    
    Piece piece;
    
    public PieceTest() {
    }
    
    @Before
    public void setUp() {
        piece = new Piece(5, 5);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void setXWorks(){
        piece.setX(10);
        assertTrue(piece.getX() == 10);
    }
    
    @Test
    public void setYWorks(){
        piece.setY(8);
        assertTrue(piece.getY() == 8);
    }
    
    @Test
    public void hitsAnotherPieceWorks(){
        Piece testPiece = new Piece(5, 5);
        assertTrue(testPiece.hitsAnother(piece));
        assertTrue(!new Piece(6, 5).hitsAnother(piece));
        assertTrue(!new Piece(0, 0).hitsAnother(piece));
        assertTrue(!new Piece(5, 6).hitsAnother(piece));
   }
    
}
