package org.example.data_access;

import org.example.connection.ConnectionFactory;
import org.example.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * adjusts the methods from the AbstractDAO class for the Product class
 */
public class ProductDAO extends AbstractDAO<Product> {

    /**
     * searches in the Product table of the database a product with a given name
     * @param productName the name of the product to be searched in the Product table of the database
     * @return all the attributes of the found product
     */
    public Product selectProductByName(String productName){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM product WHERE name = '");
        sb.append(productName);
        sb.append("'");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sb.toString());
            resultSet = statement.executeQuery();
            if(!resultSet.next()) {
            }
            else {
                Product product = new Product();
                product.setId(Integer.parseInt(resultSet.getString(1)));
                product.setName(resultSet.getString(2));
                product.setStock(Integer.parseInt(resultSet.getString(3)));
                product.setPrice(Integer.parseInt(resultSet.getString(4)));
                return product;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "product" + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
