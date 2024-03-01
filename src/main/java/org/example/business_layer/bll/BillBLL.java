package org.example.business_layer.bll;

import org.example.data_access.BillDAO;
import org.example.model.Bill;

import java.util.List;

public class BillBLL {
    private BillDAO billDAO;

    public BillBLL() {
        this.billDAO = new BillDAO();
    }

    /**
     * inserts a newly-generated bill in the Log Table
     * @param bill the bill to be introduced in the Log Table
     */
    public void insert(Bill bill) {
        billDAO.insert(bill);
    }

    /**
     * generates the data for the JTable displayed when "View bills" button is pressed
     * @return data which will populate the JTable for the "View bills" command
     */
    public Object[][] generateRowData() {
        List<Bill> resultSet = billDAO.findAll();
        Object[][] rowData = new Object[resultSet.size()][];
        for (int i = 0; i < resultSet.size(); i++) {
            Bill bill = resultSet.get(i);
            rowData[i] = new Object[7];
            rowData[i][0] = bill.orderId();
            rowData[i][1] = bill.clientName();
            rowData[i][2] = bill.productName();
            rowData[i][3] = bill.quantity();
            rowData[i][4] = bill.date();
            rowData[i][5] = bill.totalPrice();
        }
        return rowData;
    }

}
