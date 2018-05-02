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

    /**
     * The constructor for scoredao that handles the database.
     *
     * @param database Our database's name
     * @throws SQLException Exception
     */
    public ScoreDao(Database database) throws SQLException {
        this.database = database;
        this.conn = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    /**
     * Finds the high score from the database.
     *
     * @return A string that contains player and the score, (example:
     * "player1name: 100")
     * @throws SQLException Exception
     */
    public String findHighScore() throws SQLException {

        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score ORDER BY score DESC LIMIT 1;");
        ResultSet result = stat.executeQuery();
        return result.getString("player") + ": " + result.getInt("score");
    }

    /**
     * Save the player and the score to database.
     *
     * @param player The player name
     * @param score The score
     * @throws SQLException Exception
     */
    public void save(String player, Integer score) throws SQLException {
        database.update("INSERT INTO Score (player, score) VALUES(?, ?)", player, score);
    }

    /**
     * Finds ten best scores and their players from the database.
     *
     * @return A hashmap that has the player names as keys and scores as values
     * @throws SQLException Exception
     */
    public HashMap<String, Integer> findTenBest() throws SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score ORDER BY score DESC LIMIT 10;");
        ResultSet result = stat.executeQuery();
        HashMap<String, Integer> scores = new HashMap<>();
        while (result.next()) {
            scores.put(result.getString("player"), result.getInt("score"));
        }
        return scores;
    }

}
