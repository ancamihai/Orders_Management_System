package org.example.presentation.views;

import org.example.presentation.controllers.ClientManagerController;

import javax.swing.*;

import java.awt.*;

public class ClientManagerView extends JFrame {

    ClientManagerController clientManagerController = new ClientManagerController(this);

    private JTextField newClientName;
    private JTextField newClientEmail;
    private JTextField newClientAddress;

    private JComboBox clientNames;

    private JTextField clientId;

    public ClientManagerView() {
        this.prepareFrame();
        this.initialFrame();
        this.setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void prepareFrame() {
        this.setBounds(100, 100, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0x8F, 0xBC, 0x8F));
        this.getContentPane().setLayout(null);
        this.setResizable(false);
    }

    private void addButtons(){
        JButton add = new JButton("Add client");
        add.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add.setBounds(100, 420, 120, 25);
        add.setActionCommand("ADD CLIENT");
        add.addActionListener(this.clientManagerController);
        this.getContentPane().add(add);

        JButton edit = new JButton("Edit client");
        edit.setFont(new Font("Tahoma", Font.PLAIN, 12));
        edit.setBounds(470, 420, 120, 25);
        edit.setActionCommand("EDIT");
        edit.addActionListener(this.clientManagerController);
        this.getContentPane().add(edit);

        JButton delete = new JButton("Delete client");
        delete.setFont(new Font("Tahoma", Font.PLAIN, 12));
        delete.setBounds(100, 500, 120, 25);
        delete.setActionCommand("DELETE");
        delete.addActionListener(this.clientManagerController);
        this.getContentPane().add(delete);

        JButton view = new JButton("View clients");
        view.setFont(new Font("Tahoma", Font.PLAIN, 12));
        view.setBounds(470, 500, 120, 25);
        view.setActionCommand("VIEW");
        view.addActionListener(this.clientManagerController);
        this.getContentPane().add(view);
    }

    /**
     * sets the initial view of the window
     */
    public void initialFrame() {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Client manager");
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
        undo.addActionListener(this.clientManagerController);
        this.getContentPane().add(undo);
    }

    private void clientDetails() {
        JLabel name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 200, 90, 25);
        this.getContentPane().add(name);

        JLabel email = new JLabel("Email");
        email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        email.setBounds(170, 250, 90, 25);
        this.getContentPane().add(email);

        JLabel address = new JLabel("Address");
        address.setFont(new Font("Tahoma", Font.PLAIN, 15));
        address.setBounds(170, 300, 90, 25);
        this.getContentPane().add(address);

        newClientName = new JTextField();
        newClientName.setBounds(270, 200, 200, 25);
        this.getContentPane().add(newClientName);

        newClientEmail = new JTextField();
        newClientEmail.setBounds(270, 250, 200, 25);
        this.getContentPane().add(newClientEmail);

        newClientAddress = new JTextField();
        newClientAddress.setBounds(270, 300, 200, 25);
        this.getContentPane().add(newClientAddress);
    }

    /**
     * sets the view of the window when "Add client" button is pressed
     */
    public void addClientFrame() {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Client manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton newClient = new JButton("Confirm client");
        newClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
        newClient.setBounds(100, 500, 120, 25);
        newClient.setActionCommand("CONFIRM CLIENT");
        newClient.addActionListener(this.clientManagerController);
        this.getContentPane().add(newClient);

        this.addBackButton();
        this.clientDetails();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * sets the view of the window when "Edit client" button is pressed
     */
    public void editClientFrame() {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Client manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton client = new JButton("Edit client");
        client.setFont(new Font("Tahoma", Font.PLAIN, 12));
        client.setBounds(100, 500, 120, 25);
        client.setActionCommand("CHANGE CLIENT");
        client.addActionListener(this.clientManagerController);
        this.getContentPane().add(client);

        JLabel id = new JLabel("Client id");
        id.setFont(new Font("Tahoma", Font.PLAIN, 15));
        id.setBounds(170, 150, 90, 25);
        this.getContentPane().add(id);

        clientId = new JTextField();
        clientId.setBounds(270, 150, 200, 25);
        this.getContentPane().add(clientId);

        this.addBackButton();
        this.clientDetails();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * sets the view of the window when "Delete client" button is pressed
     */
    public void deleteClientFrame(Object[] clientNames) {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Client manager");
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        titleLabel.setBounds(270, 10, 350, 40);
        this.getContentPane().add(titleLabel);

        JButton client = new JButton("Delete client");
        client.setFont(new Font("Tahoma", Font.PLAIN, 12));
        client.setBounds(100, 500, 120, 25);
        client.setActionCommand("ELIMINATE CLIENT");
        client.addActionListener(this.clientManagerController);
        this.getContentPane().add(client);

        JLabel name = new JLabel("Client name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        name.setBounds(170, 200, 90, 25);
        this.getContentPane().add(name);

        this.clientNames = new JComboBox(clientNames);
        this.clientNames.setBounds(270, 200, 200, 25);
        this.getContentPane().add(this.clientNames);

        this.addBackButton();

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * view that displays in a JTable all the existing clients, found in the Client table of the database
     * @param columnNames the header of the JTable to be created, which contains the attributes of a client
     * @param rowData the data to populate the JTable generated when "View clients" button is pressed
     */
    public void viewClientsFrame(Object[] columnNames, Object[][] rowData) {
        this.getContentPane().removeAll();

        JLabel titleLabel = new JLabel("Client manager");
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
        undo.addActionListener(this.clientManagerController);
        this.getContentPane().add(undo);

        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    /**
     * @return the name of the newly-created client
     */
    public String getNewClientName() {
        return newClientName.getText();
    }

    /**
     * @return the email of the newly-created client
     */
    public String getNewClientEmail() {
        return newClientEmail.getText();
    }

    /**
     * @return the address of the newly-created client
     */
    public String getNewClientAddress() {
        return newClientAddress.getText();
    }

    /**
     * @return the name of the client to be deleted
     */
    public String getName() {
        return (String) clientNames.getItemAt(clientNames.getSelectedIndex());
    }

    /**
     * checks if the id inserted in the GUI of the client to be updated is an integer
     * @return the id of the client to be updated
     */
    public int getId() throws Exception {
        try {
            int i = Integer.parseInt(clientId.getText());
            return i;
        } catch (Exception ex) {
            throw new Exception("The id of the client must be an integer");
        }
    }
}
