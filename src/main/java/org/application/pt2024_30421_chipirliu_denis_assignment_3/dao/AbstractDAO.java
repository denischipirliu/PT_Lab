package org.application.pt2024_30421_chipirliu_denis_assignment_3.dao;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.connection.ConnectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class represents the data access object for the database.
 *
 * @param <T> The type of the object.
 */
public class AbstractDAO<T> {
    /**
     * The logger of the class.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    /**
     * This constructor creates a new data access object.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * This method creates a select query.
     *
     * @param field The field to be selected.
     * @return The select query.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(field);
        sb.append(" = ?");
        return sb.toString();
    }

    /**
     * This method creates an insert query.
     *
     * @return The insert query.
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (");
        for (Field field : type.getDeclaredFields()) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    /**
     * This method creates an update query.
     *
     * @return The update query.
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field field : type.getDeclaredFields()) {
            sb.append(field.getName());
            sb.append(" = ?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id = ?");
        return sb.toString();
    }

    /**
     * This method finds all objects of a certain type from the database.
     *
     * @return A list of all objects.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName().toLowerCase();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * This method finds an object by its id.
     *
     * @param id The id of the object.
     * @return The object.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * This method creates objects from a result set.
     *
     * @param resultSet The result set.
     * @return A list of objects.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] args = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    args[i] = resultSet.getObject(i + 1);
                }

                Constructor<T> ctor = type.getConstructor(Arrays.stream(args)
                        .map(arg -> arg == null ? null : arg.getClass())
                        .toArray(Class[]::new));

                T instance = ctor.newInstance(args);
                list.add(instance);
            }
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * This method inserts an object into the database.
     *
     * @param object The object to be inserted.
     * @return The object.
     */
    public T insert(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                statement.setObject(i, field.get(object));
                i++;
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return object;
    }

    /**
     * This method updates an object in the database.
     *
     * @param object The object to be updated.
     * @return The object.
     */
    public T update(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            Object id = null;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    id = field.get(object);
                }
                statement.setObject(i, field.get(object));
                i++;
            }
            if (id != null)
                statement.setObject(i, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return object;
    }

    /**
     * This method deletes an object from the database.
     *
     * @param object The object to be deleted.
     */
    public void delete(T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM " + type.getSimpleName().toLowerCase() + " WHERE id = ?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            Field field = type.getDeclaredField("id");
            field.setAccessible(true);
            statement.setObject(1, field.get(object));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


}
