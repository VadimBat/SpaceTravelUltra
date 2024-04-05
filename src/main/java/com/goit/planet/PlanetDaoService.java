package com.goit.planet;


import java.util.List;

/**
 * Interface describing planets objects CRUD operations.
 */

public interface PlanetDaoService {
    /**
     * Create planet object in database.
     *
     * @param planet planet object for creation and saving.
     * @return created planet object.
     */
    Planet create(Planet planet);
    /**
     * Get planet object by id from database.
     *
     * @param id unique planet identifier in database.
     * @return found planet object.
     */
    Planet getById(String id);
    /**
     * Update planet object in database.
     *
     * @param planet planet object for updating.
     * @return updated planet object.
     */
    Planet update(Planet planet);
    /**
     * Delete planet object from database.
     *
     * @param id unique planet identifier in database.
     */
    void deleteById(String id);
    /**
     * Get list of all planet objects from database.
     *
     * @return list of all planet objects.
     */
    List<Planet> getAll();
}
