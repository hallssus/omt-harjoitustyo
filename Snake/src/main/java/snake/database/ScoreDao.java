/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hallssus
 */
public class ScoreDao {

    private Database database;
    private Connection conn;

    public ScoreDao(Database database) throws SQLException {
        this.database = database;
        this.conn = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    public String findHighScore() throws SQLException {

        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score ORDER BY score DESC LIMIT 1;");
        ResultSet result = stat.executeQuery();
        return result.getString("player") + ": " + result.getInt("score");
    }

    public void save(String player, Integer score) throws SQLException {
        database.update("INSERT INTO Score (player, score) VALUES(?, ?)", player, score);
    }
    
    public HashMap<String, Integer> findTenBest() throws SQLException{
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score ORDER BY score DESC LIMIT 10;");
        ResultSet result = stat.executeQuery();
        HashMap<String, Integer> scores = new HashMap<>();
        while (result.next()){
            scores.put(result.getString("player"), result.getInt("score"));
        }
        return scores;
    }

}
