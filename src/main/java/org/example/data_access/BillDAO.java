package org.example.data_access;

import org.example.connection.ConnectionFactory;
import org.example.model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    /**
     * inserts a newly-created bill in the Bill table of the database
     * @param bill the bill to be inserted into the table
     */
    public void insert(Bill bill) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO bill (orderid, clientname, productname, quantity, date, totalprice) VALUES (?,?,?,?,?,?)");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bill.orderId());
            statement.setString(2, bill.clientName());
            statement.setString(3, bill.productName());
            statement.setInt(4, bill.quantity());
            statement.setDate(5, bill.date());
            statement.setInt(6, bill.totalPrice());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * @return all the bills created from the Bill table of the database
     */
    public List<Bill> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String string = "SELECT * FROM `Bill`";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(string);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            System.out.println(string);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    List<Bill> createObjects(ResultSet resultSet) {
        List list = new ArrayList<Object[]>();
        try {
            while (resultSet.next()) {
                Bill bill = new Bill(resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getDate(6), resultSet.getInt(7));
                list.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
