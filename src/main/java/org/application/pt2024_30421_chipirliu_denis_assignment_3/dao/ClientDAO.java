package org.application.pt2024_30421_chipirliu_denis_assignment_3.dao;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.connection.ConnectionFactory;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class represents the data access object for the client.
 */
public class ClientDAO extends AbstractDAO<Clients> {
    /**
     * This constructor creates a new client data access object.
     */
    public ClientDAO() {
        super();
    }

    private static final String findIdByName = "SELECT id FROM clients WHERE name = ?";

    /**
     * This method finds the id of a client by its name.
     *
     * @param clientName The name of the client.
     * @return The id of the client.
     */
    public Integer findIdByName(String clientName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findIdByName);
            statement.setString(1, clientName);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
