package org.example.data_access;

import org.example.connection.ConnectionFactory;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM `");
        sb.append(type.getSimpleName());
        sb.append("`");
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * @return all the data from a table of the database
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM `");
        sb.append(type.getSimpleName());
        sb.append("`");
        String query = sb.toString();
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
     * searches in a table of the database the element with the given id
     * @param id the id of the element to be searched in the table
     * @return all the attributes of the found element
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

    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    int executeQuery(String query, String type, Connection connection, PreparedStatement statement, String message) {
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();
            return 1;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, message + "DAO:" + type + " " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    private String columnNamesForInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        int nrOfColumns = t.getClass().getDeclaredFields().length;
        int currentColumn = 0;
        for (Field field : t.getClass().getDeclaredFields()) {
            try {
                if (currentColumn != 0) {
                    sb.append("`");
                    sb.append(field.getName());
                    sb.append("`");
                    if (currentColumn != (nrOfColumns - 1)) {
                        sb.append(", ");
                    }
                }
                currentColumn++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private String valuesForInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        int nrOfColumns = t.getClass().getDeclaredFields().length;
        int currentColumn = 0;
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                if (currentColumn != 0) {
                    value = field.get(t);
                    sb.append("'");
                    sb.append(value);
                    sb.append("'");
                    if (currentColumn != (nrOfColumns - 1)) {
                        sb.append(", ");
                    }
                }
                currentColumn++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `");
        sb.append(type.getSimpleName());
        sb.append("`");
        sb.append(" (");
        sb.append(columnNamesForInsertQuery(t));
        sb.append(")\n");
        sb.append("VALUES (");
        sb.append(valuesForInsertQuery(t));
        sb.append(");");
        return sb.toString();
    }

    /**
     * inserts in a table of the database an element
     * @param t the element to be inserted in the table
     * @return 1 in case the element was successfully inserted, else 0
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        String message = type.getName();
        return executeQuery(query, "insert", connection, statement, message);
    }

    private String conditionForQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("WHERE ");
        Field field = t.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        sb.append(field.getName());
        sb.append(" = ");
        try {
            sb.append(field.get(t));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String valuesForUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        int nrOfColumns = t.getClass().getDeclaredFields().length;
        int currentColumn = 0;

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                if (currentColumn != 0) {
                    value = field.get(t);
                    sb.append(field.getName());
                    sb.append(" = '");
                    sb.append(value);
                    sb.append("'");
                    if (currentColumn != (nrOfColumns - 1)) {
                        sb.append(", ");
                    }
                }
                currentColumn++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE `");
        sb.append(type.getSimpleName());
        sb.append("`");
        sb.append("\n");
        sb.append("SET ");
        sb.append(valuesForUpdateQuery(t));
        sb.append("\n");
        sb.append(conditionForQuery(t));
        return sb.toString();
    }

    /**
     * updates an element in a table of the database
     * @param t the element to be updated in the table
     * @return 1 in case the element was successfully inserted, else 0
     */
    public int update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t);
        String message = type.getName();
        return executeQuery(query, "update", connection, statement, message);
    }

    /**
     * deletes an element from a table of the database
     * @param t the element to be deleted from the table
     * @return 1 in case the element was successfully inserted, else 0
     */
    public int delete(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" ");
        sb.append("WHERE ");
        Field field = t.getClass().getDeclaredFields()[1];
        field.setAccessible(true);
        sb.append(field.getName());
        sb.append(" = '");
        try {
            sb.append(field.get(t));
            sb.append("'");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement statement = null;
        String query = sb.toString();
        String message = type.getName();
        return executeQuery(query, "delete", connection, statement, message);
    }

}
