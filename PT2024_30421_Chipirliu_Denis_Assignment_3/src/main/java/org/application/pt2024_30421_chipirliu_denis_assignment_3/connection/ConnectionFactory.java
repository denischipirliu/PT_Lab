package org.application.pt2024_30421_chipirliu_denis_assignment_3.connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the connection factory.
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/marketdb";
    private static final String USER = "root";
    private static final String PASS = "admin";
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a connection to the database.
     *
     * @return connection The connection to the database.
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error has occurred while trying to connect to the database");
            e.printStackTrace();

        }
        return connection;
    }

    /**
     * This method returns the connection to the database.
     *
     * @return connection The connection to the database.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * This method closes the connection to the database.
     *
     * @param connection The connection to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error has occurred while trying to close the connection");
                e.printStackTrace();
            }
        }
    }
    /**
     * This method closes the statement.
     *
     * @param statement The statement to be closed.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error has occurred while trying to close the statement");
                e.printStackTrace();
            }
        }
    }
    /**
     * This method closes the result set.
     *
     * @param resultSet The result set to be closed.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error has occurred while trying to close the result set");
                e.printStackTrace();
            }
        }
    }
}
