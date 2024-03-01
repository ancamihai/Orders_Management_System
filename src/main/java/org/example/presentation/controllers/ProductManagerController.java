package org.example.presentation.controllers;

import org.example.business_layer.bll.ProductBLL;
import org.example.model.Product;
import org.example.presentation.views.ProductManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductManagerController implements ActionListener {

    private ProductManagerView productManagerView;
    private ProductBLL productBLL;

    public ProductManagerController(ProductManagerView productManagerView) {
        this.productManagerView = productManagerView;
        this.productBLL = new ProductBLL();
    }

    /**
     * adds a new product to the Product table of the database, who has as attributes the ones taken from the GUI
     * a message is displayed to tell whether the product could be added or not
     */
    private void addProduct() {
        try {
            Product product = new Product(productManagerView.getNewProductName(), productManagerView.getNewProductStock(), productManagerView.getNewProductPrice());
            int i = productBLL.insertProduct(product);
            if (i == 1) {
                JOptionPane.showMessageDialog(productManagerView, "A new product was added");
                productManagerView.initialFrame();
            } else {
                JOptionPane.showMessageDialog(productManagerView, "A new product couldn't be added");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(productManagerView, ex.getMessage());
        }
    }

    /**
     * updates a product from the Product table of the database, who will have as new attributes the ones taken from the GUI
     * a message is displayed to tell whether the product could be updated or not
     */
    private void editProduct() {
        try {
            Product product = new Product(productManagerView.getId(), productManagerView.getNewProductName(), productManagerView.getNewProductStock(), productManagerView.getNewProductPrice());
            int i = productBLL.updateProduct(product);
            if (i == 1) {
                JOptionPane.showMessageDialog(productManagerView, "The product was updated");
                productManagerView.initialFrame();
            } else {
                JOptionPane.showMessageDialog(productManagerView, "The product couldn't be updated");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(productManagerView, ex.getMessage());
        }
    }

    /**
     * deletes a product from the Product table of the database, who has as name the one taken from the GUI
     * a message is displayed to tell whether the product could be deleted or not
     */
    private void deleteProduct() {
        try {
            Product product = new Product(productManagerView.getName());
            int i = productBLL.deleteProduct(product);
            if (i == 1) {
                JOptionPane.showMessageDialog(productManagerView, "The product was deleted");
                productManagerView.initialFrame();
            } else {
                JOptionPane.showMessageDialog(productManagerView, "The product couldn't be deleted");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(productManagerView, ex.getMessage());
        }
    }

    /**
     * determines which action should be performed depending on which button was pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "ADD PRODUCT") {
            this.productManagerView.addProductFrame();
        } else if (command == "EDIT") {
            this.productManagerView.editProductFrame();
        } else if (command == "DELETE") {
            productManagerView.deleteProductFrame(productBLL.productNames());
        } else if (command == "VIEW") {
            productManagerView.viewProductsFrame(productBLL.columnNames(), productBLL.rowData());
        } else if (command == "UNDO") {
            this.productManagerView.initialFrame();
        } else if (command == "CONFIRM PRODUCT") {
            this.addProduct();
        } else if (command == "CHANGE PRODUCT") {
            this.editProduct();
        } else if (command == "ELIMINATE PRODUCT") {
            this.deleteProduct();
        }
    }
}
