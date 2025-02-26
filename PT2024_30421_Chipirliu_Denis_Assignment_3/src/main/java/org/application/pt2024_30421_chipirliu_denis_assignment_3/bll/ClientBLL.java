package org.application.pt2024_30421_chipirliu_denis_assignment_3.bll;

import org.application.pt2024_30421_chipirliu_denis_assignment_3.bll.validators.ClientValidator;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.dao.ClientDAO;
import org.application.pt2024_30421_chipirliu_denis_assignment_3.model.Clients;

import java.util.List;

/**
 * This class represents the business logic layer for the client.
 */
public class ClientBLL {
    /**
     * This constructor creates a new client business logic layer.
     */
    public ClientBLL() {
    }

    /**
     * This method adds a client to the database.
     *
     * @param client The client to be added.
     */
    public void addClient(Clients client) {
        ClientValidator clientValidator = new ClientValidator();
        if (clientValidator.validateClient(client)) {
            ClientDAO clientDAO = new ClientDAO();
            clientDAO.insert(client);
        }
    }

    /**
     * This method finds all clients from the database.
     *
     * @return A list of all clients.
     */
    public List<Clients> findAll() {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findAll();
    }

    /**
     * This method updates a client from the database.
     *
     * @param client The client to be updated.
     */
    public void updateClient(Clients client) {
        ClientDAO clientDAO = new ClientDAO();
        ClientValidator clientValidator = new ClientValidator();
        if (clientValidator.validateClient(client)) {
            clientDAO.update(client);
        }
    }

    /**
     * This method finds the id of a client by its name.
     *
     * @param value The name of the client.
     * @return The id of the client.
     */
    public Integer findIdByName(String value) {
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findIdByName(value);
    }

    /**
     * This method deletes a client from the database.
     *
     * @param selectedClient The client to be deleted.
     */
    public void deleteClient(Clients selectedClient) {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.delete(selectedClient);
    }
}
