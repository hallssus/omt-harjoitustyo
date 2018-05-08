/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.database;

import java.sql.SQLException;
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
public class ScoreDaoTest {

    private Database db;
    private ScoreDao scoreDao;

    public ScoreDaoTest() {
    }

    @Before
    public void setUp() throws Exception {
        db = new Database("jdbc:sqlite:database.db");
        scoreDao = new ScoreDao(db);
    }

    @After
    public void tearDown() throws SQLException {
        scoreDao.delete("testplayer");
        scoreDao.delete("testplayer3");
        scoreDao.delete("testplayer4");
        scoreDao.delete("testplayer5");
        scoreDao.delete("testplayer6");
        scoreDao.delete("testplayer7");
        scoreDao.delete("testplayer8");
        scoreDao.delete("testplayer9");
        scoreDao.delete("testplayer10");
        scoreDao.delete("testplayer11");
        scoreDao.delete("testplayer12");
        scoreDao.delete("testplayer13");
        scoreDao.delete("testplayer14");
        scoreDao.delete("testplayer15");

    }

    @Test
    public void saveMethodWorks() throws SQLException {
        scoreDao.save("testplayer", 100);
        assertTrue(scoreDao.contains("testplayer"));
    }

    @Test
    public void findTenBestWorks() throws SQLException {
        ArrayList<String> results = scoreDao.findTenBest();
        String[] first = results.get(0).split(":");
        String[] second = results.get(1).split(":");
        assertTrue(Integer.parseInt(first[1]) >= Integer.parseInt(second[1]));
    }

    @Test
    public void deleteWorks() throws SQLException {
        scoreDao.save("testpayer2", 100000);
        scoreDao.delete("testplayer2");
        assertTrue(!scoreDao.contains("testplayer2"));
    }

    @Test
    public void deleteWorstScoreWorks() throws SQLException {
        scoreDao.save("testplayer3", -1);
        assertTrue(scoreDao.contains("testplayer3"));
        scoreDao.deleteWorstScore();
        assertTrue(!scoreDao.contains("testplayer3"));
    }

    @Test
    public void containsWorks() throws SQLException {
        scoreDao.save("testplayer4", 10);
        assertTrue(scoreDao.contains("testplayer4"));
    }

    @Test
    public void getDatabaseSizeWorks() throws SQLException {
        scoreDao.save("testplayer5", 100);
        scoreDao.save("testplayer6", 100);
        scoreDao.save("testplayer7", 100);
        scoreDao.save("testplayer8", 100);
        scoreDao.save("testplayer9", 100);
        scoreDao.save("testplayer10", 100);
        scoreDao.save("testplayer11", 100);
        scoreDao.save("testplayer12", 100);
        scoreDao.save("testplayer13", 100);
        scoreDao.save("testplayer14", 100);
        scoreDao.save("testplayer15", 100);
        int size = scoreDao.getDatabaseSize();
        assertTrue(size > 10);
    }

}
