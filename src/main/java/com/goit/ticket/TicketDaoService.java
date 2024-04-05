package com.goit.ticket;

import java.util.List;

/**
 * Interface describing tickets objects CRUD operations.
 */

public interface TicketDaoService {
    /**
     * Create ticket object in database.
     *
     * @param ticket ticket object for creation and saving.
     * @return created ticket object.
     */
    Ticket create(Ticket ticket);
    /**
     * Get ticket object by id from database.
     *
     * @param id unique ticket identifier in database.
     * @return found ticket object.
     */
    Ticket getById(Long id);
    /**
     * Update ticket object in database.
     *
     * @param ticket ticket object for updating.
     * @return updated ticket object.
     */
    Ticket update(Ticket ticket);
    /**
     * Delete ticket object from database.
     *
     * @param id unique ticket identifier in database.
     */
    void deleteById(Long id);
    /**
     * Get list of all ticket objects from database.
     *
     * @return list of all ticket objects.
     */
    List<Ticket> getAll();

}
