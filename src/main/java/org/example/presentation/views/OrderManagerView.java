package org.example.presentation.views;

import org.example.presentation.controllers.OrderManagerController;

import javax.swing.*;
import java.awt.*;

public class OrderManagerView extends JFrame {

    private JTextField selectedOrder;

    OrderManagerController orderManagerController=new OrderManagerController(this);

    private JComboBox clientNames;

    private JComboBox productNames;

    private JTextField quantity;

    public OrderManagerView() {
        this.prepareFrame();
        this.initialFrame();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void prepareFrame() {
        this.setBounds(100, 100, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xE9,0x96,0x7A));
        this.getContentPane().setLayout(null);
        this.setResizable(false);
    }

    /**
     * sets the initial view of the window
     */
    public void initialFrame(){
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Order manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(275, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton newOrder = new JButton("New order");
        newOrder.setFont(new Font("Tahoma", Font.PLAIN, 12));
        newOrder.setBounds(90, 500, 130, 25);
        newOrder.setActionCommand("NEW ORDER");
        newOrder.addActionListener(this.orderManagerController);
        this.getContentPane().add(newOrder);

        JButton cancelOrder = new JButton("Cancel order");
        cancelOrder.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cancelOrder.setBounds(290, 500, 130, 25);
        cancelOrder.setActionCommand("CANCEL");
        cancelOrder.addActionListener(this.orderManagerController);
        this.getContentPane().add(cancelOrder);

        JButton view = new JButton("View orders");
        view.setFont(new Font("Tahoma", Font.PLAIN, 12));
        view.setBounds(490, 500, 130, 25);
        view.setActionCommand("VIEW");
        view.addActionListener(this.orderManagerController);
        this.getContentPane().add(view);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void addBackToMenuButton() {
        JButton undo = new JButton("Back");
        undo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        undo.setBounds(470, 500, 120, 25);
        undo.setActionCommand("UNDO");
        undo.addActionListener(this.orderManagerController);
        this.getContentPane().add(undo);
    }

    private void addBackProductButton() {
        JButton undo = new JButton("Back");
        undo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        undo.setBounds(470, 500, 120, 25);
        undo.setActionCommand("UNDO PRODUCT");
        undo.addActionListener(this.orderManagerController);
        this.getContentPane().add(undo);
    }

    private void addQuantityField(){
        quantity = new JTextField();
        quantity.setBounds(270, 250, 200, 25);
        this.getContentPane().add(quantity);

        JLabel name = new JLabel("Quantity");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 250, 90, 25);
        this.getContentPane().add(name);
    }

    /**
     * sets the view of the window when "New order" button is pressed, where a client must be selected for the order
     */
    public void SelectClientFrame(Object[] clientNames){
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Order manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(275, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton client = new JButton("Confirm client");
        client.setFont(new Font("Tahoma", Font.PLAIN, 12));
        client.setBounds(100, 500, 120, 25);
        client.setActionCommand("CONFIRM CLIENT");
        client.addActionListener(this.orderManagerController);
        this.getContentPane().add(client);

        JLabel name = new JLabel("Client name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 200, 90, 25);
        this.getContentPane().add(name);

        this.clientNames = new JComboBox(clientNames);
        this.clientNames.setBounds(270, 200, 200, 25);
        this.getContentPane().add(this.clientNames);

        this.addBackToMenuButton();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * sets the view of the window when "Confirm client" button is pressed, where a product and a quantity for it must be selected for the order
     */
    public void SelectProductFrame(Object[] productNames){
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Order manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(275, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton order = new JButton("Confirm order");
        order.setFont(new Font("Tahoma", Font.PLAIN, 12));
        order.setBounds(100, 500, 120, 25);
        order.setActionCommand("CONFIRM ORDER");
        order.addActionListener(this.orderManagerController);
        this.getContentPane().add(order);

        JLabel name = new JLabel("Product name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 200, 120, 25);
        this.getContentPane().add(name);

        this.productNames = new JComboBox(productNames);
        this.productNames.setBounds(270, 200, 200, 25);
        this.getContentPane().add(this.productNames);

        this.addBackProductButton();
        this.addQuantityField();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * sets the view of the window when "Cancel order" button is pressed
     */
    public void cancelOrderFrame(){
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Order manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(275, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton order = new JButton("Delete order");
        order.setFont(new Font("Tahoma", Font.PLAIN, 12));
        order.setBounds(100, 500, 120, 25);
        order.setActionCommand("DELETE ORDER");
        order.addActionListener(this.orderManagerController);
        this.getContentPane().add(order);

        JLabel id = new JLabel("Order id");
        id.setFont(new Font("Tahoma", Font.PLAIN, 15));
        id.setBounds(170, 200, 120, 25);
        this.getContentPane().add(id);

        this.selectedOrder=new JTextField();
        this.selectedOrder.setBounds(270,200,200,25);
        this.getContentPane().add(selectedOrder);

        this.addBackToMenuButton();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * view that displays in a JTable all the existing orders, found in the Order table of the database
     * @param columnNames the header of the JTable to be created, which contains the attributes of an order
     * @param rowData the data to populate the JTable generated when "View orders" button is pressed
     */
    public void viewOrdersFrame(Object[] columnNames, Object[][] rowData){
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Order manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(275, 10, 350, 40);
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
        undo.addActionListener(this.orderManagerController);
        this.getContentPane().add(undo);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * @return the name of the client of the order
     */
    public String getClientName() {
        return (String) clientNames.getItemAt(clientNames.getSelectedIndex());
    }

    /**
     * @return the name of the product of the order
     */
    public String getProductName() {
        return (String) productNames.getItemAt(productNames.getSelectedIndex());
    }

    /**
     * checks if the quantity inserted in the GUI of the product to be ordered is an integer
     * @return the quantity of the product to be ordered
     */
    public int getQuantity() throws Exception {
        try {
            int i = Integer.parseInt(quantity.getText());
            return i;
        } catch (Exception ex) {
            throw new Exception("The quantity must be an integer");
        }
    }

    /**
     * checks if the id inserted in the GUI of the order to be cancelled is an integer
     * @return the id of the order to be cancelled
     */
    public int getId() throws Exception {
        try {
            int i = Integer.parseInt(selectedOrder.getText());
            return i;
        } catch (Exception ex) {
            throw new Exception("The id must be an integer");
        }
    }
}
