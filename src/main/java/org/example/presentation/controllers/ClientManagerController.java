package org.example.presentation.controllers;

import org.example.business_layer.bll.ClientBLL;
import org.example.model.Client;
import org.example.presentation.views.ClientManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientManagerController implements ActionListener {
    private ClientManagerView clientManagerView;
    private ClientBLL clientBLL;

    public ClientManagerController(ClientManagerView clientManagerView) {
        this.clientManagerView = clientManagerView;
        this.clientBLL = new ClientBLL();
    }

    /**
     * adds a new client to the Client table of the database, who has as attributes the ones taken from the GUI
     * a message is displayed to tell whether the client could be added or not
     */
    private void addClient() {
        Client client = new Client(clientManagerView.getNewClientName(), clientManagerView.getNewClientEmail(), clientManagerView.getNewClientAddress());
        try {
            int i = clientBLL.insertClient(client);
            if (i == 1) {
                JOptionPane.showMessageDialog(clientManagerView, "A new client was added");
                clientManagerView.initialFrame();
            } else {
                JOptionPane.showMessageDialog(clientManagerView, "A new client couldn't be added");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(clientManagerView, ex.getMessage());
        }
    }

    /**
     * updates a client from the Client table of the database, who will have as new attributes the ones taken from the GUI
     * a message is displayed to tell whether the client could be updated or not
     */
    private void editClient() {
        try {
            Client client = new Client(clientManagerView.getId(), clientManagerView.getNewClientName(), clientManagerView.getNewClientEmail(), clientManagerView.getNewClientAddress());
            int i = clientBLL.updateClient(client);
            if (i == 1) {
                JOptionPane.showMessageDialog(clientManagerView, "The client was updated");
                clientManagerView.initialFrame();
            } else {
                JOptionPane.showMessageDialog(clientManagerView, "The client couldn't be updated");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(clientManagerView, ex.getMessage());
        }
    }

    /**
     * deletes a client from the Client table of the database, who has as name the one taken from the GUI
     * a message is displayed to tell whether the client could be deleted or not
     */
    private void deleteClient() {
        try {
            Client client = new Client(clientManagerView.getName());
            int i = clientBLL.deleteClient(client);
            if (i == 1) {
                JOptionPane.showMessageDialog(clientManagerView, "The client was deleted");
                clientManagerView.initialFrame();
            } else {
                JOptionPane.showMessageDialog(clientManagerView, "The client couldn't be deleted");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(clientManagerView, ex.getMessage());
        }
    }

    /**
     * determines which action should be performed depending on which button was pressed
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "ADD CLIENT") {
            clientManagerView.addClientFrame();
        } else if (command == "EDIT") {
            clientManagerView.editClientFrame();
        } else if (command == "DELETE") {
            clientManagerView.deleteClientFrame(clientBLL.clientNames());
        } else if(command=="VIEW"){
            clientManagerView.viewClientsFrame(clientBLL.columnNames(),clientBLL.rowData());
        }
        else if (command == "UNDO") {
            clientManagerView.initialFrame();
        } else if (command == "CONFIRM CLIENT") {
            this.addClient();
        } else if (command == "CHANGE CLIENT") {
            this.editClient();
        } else if (command == "ELIMINATE CLIENT") {
            this.deleteClient();
        }
    }
}
