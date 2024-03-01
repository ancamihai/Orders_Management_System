package org.example.presentation.views;

import org.example.presentation.controllers.StartController;

import javax.swing.*;
import java.awt.*;

public class StartView extends JFrame {

    private JButton client;

    private JButton product;

    private JButton order;

    private JButton bill;

    StartController startController = new StartController(this);

    public StartView() {
        this.prepareFrame();
        this.setVisible(true);
    }

    private void addButtons(){
        client = new JButton("Client manager");
        client.setFont(new Font("Tahoma", Font.PLAIN, 12));
        client.setBounds(40, 500, 130, 25);
        client.setActionCommand("CLIENT");
        client.addActionListener(this.startController);
        this.getContentPane().add(client);

        product = new JButton("Product manager");
        product.setFont(new Font("Tahoma", Font.PLAIN, 12));
        product.setBounds(200, 500, 130, 25);
        product.setActionCommand("PRODUCT");
        product.addActionListener(this.startController);
        this.getContentPane().add(product);

        order = new JButton("Order manager");
        order.setFont(new Font("Tahoma", Font.PLAIN, 12));
        order.setBounds(360, 500, 130, 25);
        order.setActionCommand("ORDER");
        order.addActionListener(this.startController);
        this.getContentPane().add(order);

        bill= new JButton("View bills");
        bill.setFont(new Font("Tahoma", Font.PLAIN, 12));
        bill.setBounds(520, 500, 120, 25);
        bill.setActionCommand("BILL");
        bill.addActionListener(this.startController);
        this.getContentPane().add(bill);
    }

    /**
     * sets the initial view of the window
     */
    public void prepareFrame() {
        this.getContentPane().removeAll();

        this.setBounds(100, 100, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xac, 0x86, 0xa8));
        this.getContentPane().setLayout(null);
        this.setResizable(false);

        JLabel titleLabel = new JLabel("Orders management");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(250, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        addButtons();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * view that displays in a JTable all the generated bills, found in the Bill table of the database
     * @param columnNames the header of the JTable to be created, which contains the attributes of a bill
     * @param rowData the data to populate the JTable generated when "View bills" button is pressed
     */
    public void viewBillsFrame(Object[] columnNames, Object[][] rowData){
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Orders management");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(250, 10, 350, 40);
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
        undo.addActionListener(this.startController);
        this.getContentPane().add(undo);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

}
