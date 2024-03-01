package org.example.presentation.controllers;

import org.example.business_layer.bll.ClientBLL;
import org.example.business_layer.bll.OrderBLL;
import org.example.business_layer.bll.ProductBLL;
import org.example.presentation.views.OrderManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderManagerController implements ActionListener {

    private OrderManagerView orderManagerView;

    private ClientBLL clientBLL;

    private OrderBLL orderBLL;

    private ProductBLL productBLL;

    public OrderManagerController(OrderManagerView orderManagerView) {
        this.orderManagerView = orderManagerView;
        this.clientBLL = new ClientBLL();
        this.orderBLL = new OrderBLL();
        this.productBLL = new ProductBLL();
    }

    /**
     * if an order could be finalized, the content of the bill associated with it will be displayed
     * else a message with what went wrong will be displayed (under-stock, wrong data for the fields of the order)
     */
    private void confirmOrder() {
        try {
            String message = orderBLL.confirmOrder(this.orderManagerView.getProductName(), this.orderManagerView.getQuantity(), productBLL);
            JOptionPane.showMessageDialog(this.orderManagerView, message);
            orderManagerView.initialFrame();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.orderManagerView, ex.getMessage());
        }
    }

    /**
     * cancels an available order by setting its available attribute from "Yes" to "No"
     * the order to be cancelled has its id taken from the GUI
     * displays a message if the order could be finalized or whether the order was already cancelled
     */
    private void deleteOrder() {
        try {
            int i = orderBLL.cancelOrder(this.orderManagerView.getId());
            if (i == 1) {
                JOptionPane.showMessageDialog(this.orderManagerView, "The order is already cancelled!");
            } else {
                JOptionPane.showMessageDialog(this.orderManagerView, "The order was cancelled!");
                orderManagerView.initialFrame();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this.orderManagerView, ex.getMessage());
        }
    }

    /**
     * determines which action should be performed depending on which button was pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "NEW ORDER") {
            orderManagerView.SelectClientFrame(clientBLL.clientNames());
            orderBLL.newOrder();
        } else if (command == "VIEW") {
            orderManagerView.viewOrdersFrame(orderBLL.columnNames(), orderBLL.rowData());
        } else if (command == "CANCEL") {
            orderManagerView.cancelOrderFrame();
        } else if (command == "CONFIRM CLIENT") {
            orderBLL.confirmClient(this.orderManagerView.getClientName());
            orderManagerView.SelectProductFrame(productBLL.productNames());
        } else if (command == "CONFIRM ORDER") {
            confirmOrder();
        } else if (command == "DELETE ORDER") {
            deleteOrder();
        } else if (command == "UNDO PRODUCT") {
            orderManagerView.SelectClientFrame(clientBLL.clientNames());
        } else if (command == "UNDO") {
            orderManagerView.initialFrame();
        }
    }
}
