package org.example.business_layer.bll;

import org.example.business_layer.validators.EmailValidator;
import org.example.business_layer.table_generators.ClientTableGenerator;
import org.example.data_access.ClientDAO;
import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBLL {

    private ClientDAO clientDAO;
    private ClientTableGenerator clientTableGenerator;

    public ClientBLL() {
        this.clientDAO = new ClientDAO();
        this.clientTableGenerator = new ClientTableGenerator();
    }

    /**
     * checks if the newly-inserted client has an appropriate email and inserts it in the Client table of the database
     * @param client the client to be inserted
     * @return 1 in case the client was successfully inserted, else 0
     */
    public int insertClient(Client client) {
        EmailValidator.validate(client);
        return clientDAO.insert(client);
    }

    /**
     * checks if the client whose data was updated has an appropriate email and updates it in the Client table of the database
     * @param client the client to be updated, identified by its id
     * @return 1 in case the client was successfully updated, else 0
     */
    public int updateClient(Client client) {
        EmailValidator.validate(client);
        return clientDAO.update(client);
    }


    /**
     * deletes a client from the Client table of the database
     * @param client the client to be deleted, identified by its name
     * @return 1 in case the client was successfully deleted, else 0
     */
    public int deleteClient(Client client) {
        return clientDAO.delete(client);
    }

    /**
     *
     * @return the header of the JTable generated when "View clients" button is pressed
     */
    public Object[] columnNames() {
        List allClients = clientDAO.findAll();
        return clientTableGenerator.generateColumnNames(allClients);
    }

    /**
     *
     * @return the data to populate the JTable generated when "View clients" button is pressed
     */
    public Object[][] rowData() {
        List allClients = clientDAO.findAll();
        return clientTableGenerator.generateRowData(allClients);
    }

    /**
     *
     * @return the names of the currently existing clients in the Client table of the database
     */
    public Object[] clientNames(){
        List <Client> allClients=clientDAO.findAll();
        ArrayList clientsNames=new ArrayList<>();
        for(int i=0; i< allClients.size(); i++){
            clientsNames.add(allClients.get(i).getName());
        }
        return clientsNames.toArray();
    }


}
