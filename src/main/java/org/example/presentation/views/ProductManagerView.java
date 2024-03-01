package org.example.presentation.views;

import org.example.presentation.controllers.ProductManagerController;

import javax.swing.*;
import java.awt.*;

public class ProductManagerView extends JFrame {

    ProductManagerController productManagerController = new ProductManagerController(this);

    private JTextField newProductName;
    private JTextField newProductStock;
    private JTextField newProductPrice;
    private JTextField productId;
    private JComboBox productNames;

    public ProductManagerView() {
        this.prepareFrame();
        this.initialFrame();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void prepareFrame() {
        this.setBounds(100, 100, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x87, 0xCE, 0xEB));
        this.getContentPane().setLayout(null);
        this.setResizable(false);
    }

    private void addButtons() {
        JButton add = new JButton("Add product");
        add.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add.setBounds(100, 420, 120, 25);
        add.setActionCommand("ADD PRODUCT");
        add.addActionListener(this.productManagerController);
        this.getContentPane().add(add);

        JButton edit = new JButton("Edit product");
        edit.setFont(new Font("Tahoma", Font.PLAIN, 12));
        edit.setBounds(470, 420, 120, 25);
        edit.setActionCommand("EDIT");
        edit.addActionListener(this.productManagerController);
        this.getContentPane().add(edit);

        JButton delete = new JButton("Delete product");
        delete.setFont(new Font("Tahoma", Font.PLAIN, 12));
        delete.setBounds(100, 500, 120, 25);
        delete.setActionCommand("DELETE");
        delete.addActionListener(this.productManagerController);
        this.getContentPane().add(delete);

        JButton view = new JButton("View products");
        view.setFont(new Font("Tahoma", Font.PLAIN, 12));
        view.setBounds(470, 500, 120, 25);
        view.setActionCommand("VIEW");
        view.addActionListener(this.productManagerController);
        this.getContentPane().add(view);
    }

    /**
     * sets the initial view of the window
     */
    public void initialFrame() {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Product manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        addButtons();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void addBackButton() {
        JButton undo = new JButton("Back");
        undo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        undo.setBounds(470, 500, 120, 25);
        undo.setActionCommand("UNDO");
        undo.addActionListener(this.productManagerController);
        this.getContentPane().add(undo);
    }

    private void productDetails() {
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 200, 90, 25);
        this.getContentPane().add(name);

        JLabel email = new JLabel("Stock");
        email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        email.setBounds(170, 250, 90, 25);
        this.getContentPane().add(email);

        JLabel address = new JLabel("Price");
        address.setFont(new Font("Tahoma", Font.PLAIN, 15));
        address.setBounds(170, 300, 90, 25);
        this.getContentPane().add(address);

        newProductName = new JTextField();
        newProductName.setBounds(270, 200, 200, 25);
        this.getContentPane().add(newProductName);

        newProductStock = new JTextField();
        newProductStock.setBounds(270, 250, 200, 25);
        this.getContentPane().add(newProductStock);

        newProductPrice = new JTextField();
        newProductPrice.setBounds(270, 300, 200, 25);
        this.getContentPane().add(newProductPrice);
    }

    /**
     * sets the view of the window when "Add product" button is pressed
     */
    public void addProductFrame() {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Product manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton newProduct = new JButton("Confirm product");
        newProduct.setFont(new Font("Tahoma", Font.PLAIN, 12));
        newProduct.setBounds(100, 500, 120, 25);
        newProduct.setActionCommand("CONFIRM PRODUCT");
        newProduct.addActionListener(this.productManagerController);
        this.getContentPane().add(newProduct);

        this.addBackButton();
        this.productDetails();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * sets the view of the window when "Edit product" button is pressed
     */
    public void editProductFrame() {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Product manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton product = new JButton("Edit product");
        product.setFont(new Font("Tahoma", Font.PLAIN, 12));
        product.setBounds(100, 500, 120, 25);
        product.setActionCommand("CHANGE PRODUCT");
        product.addActionListener(this.productManagerController);
        this.getContentPane().add(product);

        JLabel id = new JLabel("Product id");
        id.setFont(new Font("Tahoma", Font.PLAIN, 15));
        id.setBounds(170, 150, 90, 25);
        this.getContentPane().add(id);

        productId = new JTextField();
        productId.setBounds(270, 150, 200, 25);
        this.getContentPane().add(productId);

        this.addBackButton();
        this.productDetails();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * sets the view of the window when "Delete product" button is pressed
     */
    public void deleteProductFrame(Object[] productNames) {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Product manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton product = new JButton("Delete product");
        product.setFont(new Font("Tahoma", Font.PLAIN, 12));
        product.setBounds(100, 500, 120, 25);
        product.setActionCommand("ELIMINATE PRODUCT");
        product.addActionListener(this.productManagerController);
        this.getContentPane().add(product);

        JLabel name = new JLabel("Product name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 200, 120, 25);
        this.getContentPane().add(name);

        this.productNames = new JComboBox(productNames);
        this.productNames.setBounds(300, 200, 200, 25);
        this.getContentPane().add(this.productNames);

        this.addBackButton();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * view that displays in a JTable all the existing products, found in the Product table of the database
     * @param columnNames the header of the JTable to be created, which contains the attributes of a product
     * @param rowData     the data to populate the JTable generated when "View products" button is pressed
     */
    public void viewProductsFrame(Object[] columnNames, Object[][] rowData) {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Product manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JTable jTable = new JTable(rowData, columnNames);
        jTable.setBounds(40, 70, 600, 400);
        JScrollPane scrollable = new JScrollPane(jTable);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollable.setBounds(40, 70, 600, 400);
        scrollable.setPreferredSize(new Dimension(600, 400));
        scrollable.setSize(new Dimension(600, 400));
        this.getContentPane().add(scrollable);

        JButton undo = new JButton("Back");
        undo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        undo.setBounds(270, 500, 120, 25);
        undo.setActionCommand("UNDO");
        undo.addActionListener(this.productManagerController);
        this.getContentPane().add(undo);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * @return the name of the newly-created product
     */
    public String getNewProductName() {
        return newProductName.getText();
    }

    /**
     * checks if the stock inserted in the GUI for the product is an integer
     * @return the new stock of the product
     */
    public int getNewProductStock() throws Exception {
        try {
            int i = Integer.parseInt(newProductStock.getText());
            return i;
        } catch (Exception ex) {
            throw new Exception("The stock of the product must be an integer");
        }
    }

    /**
     * checks if the price inserted in the GUI for the product is an integer
     * @return the new price of the product
     */
    public int getNewProductPrice() throws Exception {
        try {
            int i = Integer.parseInt(newProductPrice.getText());
            return i;
        } catch (Exception ex) {
            throw new Exception("The price of the product must be an integer");
        }
    }

    /**
     * checks if the id inserted in the GUI of the product to be updated is an integer
     * @return the id of the product to be updated
     */
    public int getId() throws Exception {
        try {
            int i = Integer.parseInt(productId.getText());
            return i;
        } catch (Exception ex) {
            throw new Exception("The id of the product must be an integer");
        }
    }

    /**
     * @return the name of the product to be deleted
     */
    public String getName() {
        return (String) productNames.getItemAt(productNames.getSelectedIndex());
    }
}
