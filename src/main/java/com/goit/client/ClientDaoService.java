package com.goit.client;

import java.util.List;

/**
 * Interface describing clients objects CRUD operations.
 */
public interface ClientDaoService {
    /**
     * Create client object in database.
     *
     * @param client client object for creation and saving.
     * @return created client object.
     */
    Client create(Client client);
    /**
     * Get client object by id from database.
     *
     * @param id unique client identifier in database.
     * @return found client object.
     */
    Client getById(Long id);
    /**
     * Update client object in database.
     *
     * @param client client object for updating.
     * @return updated client object.
     */
    Client update(Client client);
    /**
     * Delete client object from database.
     *
     * @param id unique client identifier in database.
     */
    void deleteById(Long id);
    /**
     * Get list of all client objects from database.
     *
     * @return list of all client objects.
     */
    List<Client> getAll();
}
