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
     * The constructor for ScoreDao that handles the database.
     *
     * @param database Our database's name
     * @throws SQLException Exception
     */
    public ScoreDao(Database database) throws SQLException {
        this.database = database;
        this.conn = DriverManager.getConnection("jdbc:sqlite:database.db");
    }

    /**
     * Save the player and the score to database.
     *
     * @param player The player name
     * @param score The score
     * @throws SQLException Exception
     */
    public void save(String player, Integer score) throws SQLException {
        database.update("INSERT INTO Score (player, score) VALUES(?, ?);", player, score);
    }

    /**
     * Finds ten best scores and their players from the database.
     *
     * @return A list that has the player names and scores in it, separated with
     * a ":"
     * @throws SQLException Exception
     */
    public ArrayList<String> findTenBest() throws SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score ORDER BY score DESC LIMIT 10;");
        ResultSet result = stat.executeQuery();
        ArrayList<String> results = new ArrayList<>();

        while (result.next()) {
            String nameandscore = result.getString("player") + ":" + result.getInt("score");
            results.add(nameandscore);
        }
        return results;
    }

    /**
     * Deletes the worst score on the database
     *
     * @throws SQLException if something goes wrong
     */
    public void deleteWorstScore() throws SQLException {

        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score ORDER BY score DESC;");
        ResultSet result = stat.executeQuery();
        ArrayList<String> scores = new ArrayList<>();

        while (result.next()) {
            String nameandscore = result.getString("player") + ":" + result.getInt("score");
            scores.add(nameandscore);
        }

        String[] parts = scores.get(scores.size() - 1).split(":");
        String smallestPlayer = parts[0];
        this.database.update("DELETE FROM Score WHERE player = ?;", smallestPlayer);
    }

    /**
     * Deletes the given player from the database
     *
     * @param player That will be deleted
     * @throws SQLException
     */
    public void delete(String player) throws SQLException {
        this.database.update("DELETE FROM Score WHERE player = ?", player);
    }

    /**
     * Check if a player is in the database
     *
     * @param player That needs to be checked
     * @return True if the player is found
     * @throws SQLException if something goes wrong
     */
    public boolean contains(String player) throws SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score;");
        ResultSet result = stat.executeQuery();
        ArrayList<String> scores = new ArrayList<>();
        while (result.next()) {
            scores.add(result.getString("player"));
        }
        if (scores.contains(player)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets the size of the database
     *
     * @return The size
     * @throws SQLException if something goes wrong
     */
    public int getDatabaseSize() throws SQLException {
        PreparedStatement stat = conn.prepareStatement("SELECT * FROM Score;");
        ResultSet result = stat.executeQuery();
        ArrayList<String> scores = new ArrayList<>();
        while (result.next()) {
            scores.add(result.getString("player"));
        }
        return scores.size();
    }
}
