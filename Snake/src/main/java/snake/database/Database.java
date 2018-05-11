package snake.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private boolean debug;
    private Connection connection;
    private String databaseAddress;

    public Database(String address) throws Exception {
        this.connection = DriverManager.getConnection(address);
        this.databaseAddress = address;
        init();
    }

    /**
     * Updates the database with the given command
     *
     * @param updateQuery The command
     * @param params Parameters if needed
     * @return Row count of the table or 0 if nothing is done
     * @throws SQLException if something goes wrong
     */
    public int update(String updateQuery, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        int changes = stmt.executeUpdate();
        stmt.close();
        return changes;
    }

    /**
     * Get the connection
     *
     * @return The connection
     * @throws SQLException if something goes wrong
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Initialize the database if there is not one already.
     */
    private void init() {
        String sentences = null;
        sentences = sqliteCommands();

        // "try with resources" closes the resource automaticly in the end
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // executing commands
            st.executeUpdate(sentences);

        } catch (Throwable t) {
            // If database excists, nothing will be done

        }
    }

    /**
     * The commands to create the database.
     *
     * @return
     */
    public String sqliteCommands() {
        return "PRAGMA foreign_keys=ON; "
                + "CREATE TABLE Score (id integer PRIMARY KEY, player varchar(50), score integer);";
    }

}
