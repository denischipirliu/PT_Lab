package org.application.pt2024_30421_chipirliu_denis_assignment_3.dao;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.connection.ConnectionFactory;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * This class represents the data access object for the product.
 */
public class ProductDAO extends AbstractDAO<Products> {
    private static final String findIdByName = "SELECT id FROM products WHERE name = ?";
    private static final String findQuantityById = "SELECT quantity FROM products WHERE id = ?";
    private static final String updateQuantity = "UPDATE products SET quantity = ? WHERE id = ?";

    /**
     * This constructor creates a new product data access object.
     */
    public ProductDAO() {
        super();
    }

    /**
     * This method finds the id of a product by its name.
     *
     * @param productName The name of the product.
     * @return The id of the product.
     */
    public Integer findIdByName(String productName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findIdByName);
            statement.setString(1, productName);
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

    /**
     * This method finds the stock of a product by its id.
     *
     * @param productId The id of the product.
     * @return The stock of the product.
     */
    public Integer findStockById(Integer productId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findQuantityById);
            statement.setInt(1, productId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
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

    /**
     * This method updates the stock of a product by its id.
     *
     * @param productId The id of the product.
     * @param newStock  The new stock of the product.
     */
    public void updateStock(Integer productId, Integer newStock) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateQuantity);
            statement.setInt(1, newStock);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
