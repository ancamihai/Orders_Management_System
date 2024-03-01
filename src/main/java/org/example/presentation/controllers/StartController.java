package org.example.presentation.controllers;

import org.example.business_layer.bll.BillBLL;
import org.example.presentation.views.ClientManagerView;
import org.example.presentation.views.OrderManagerView;
import org.example.presentation.views.ProductManagerView;
import org.example.presentation.views.StartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartController implements ActionListener {

    private StartView startView;

    private BillBLL billBLL;

    public StartController(StartView startView) {
        this.startView = startView;
        this.billBLL = new BillBLL();
    }

    /**
     * determines which action should be performed depending on which button was pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "CLIENT") {
            ClientManagerView clientManagerView = new ClientManagerView();
        } else if (command == "PRODUCT") {
            ProductManagerView productManagerView = new ProductManagerView();
        } else if (command == "ORDER") {
            OrderManagerView orderManagerView = new OrderManagerView();
        } else if (command == "UNDO") {
            this.startView.prepareFrame();
        } else if (command == "BILL") {
            Object[] columnNames = {"OrderId", "ClientName", "ProductName", "Quantity", "Date", "Price"};
            this.startView.viewBillsFrame(columnNames, billBLL.generateRowData());
        }
    }
}
