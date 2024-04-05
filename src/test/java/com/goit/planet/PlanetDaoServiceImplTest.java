package com.goit.planet;

import com.goit.storage.DbInitService;
import com.goit.storage.HibernateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class PlanetDaoServiceImplTest {
    private HibernateUtil connection;
    private PlanetDaoServiceImpl planetService;
    private DbInitService init;

    @BeforeEach
    void beforeEach() {
        final String DB_URL = "jdbc:h2:mem:database;DB_CLOSE_DELAY=-1";
        planetService = new PlanetDaoServiceImpl();
        connection = HibernateUtil.getInstance();
        init = new DbInitService();
        init.initDb(DB_URL);
    }

    @AfterEach
    void afterEach() {
        connection.close();
    }


    @Test
    void getAllTest() {

        //Given
        int actualSize = 5;

        //When
        List<com.goit.planet.Planet> expectedClients = planetService.getAll();

        //Then
        Assertions.assertEquals(expectedClients.size(), actualSize);

    }


    @Test
    void createTest() {

        //Given
        Planet originalPlanet = new Planet();
        originalPlanet.setId("TS");
        originalPlanet.setName("TestName");
        String originalName = originalPlanet.getName();

        //When
        Planet expectedPlanet = planetService.create(originalPlanet);
        String expectedName = expectedPlanet.getName();

        //Then
        Assertions.assertEquals(expectedName, originalName);

    }

    @Test
    void getByIdTest() {

        //Given
        Planet originalPlanet = new Planet();
        originalPlanet.setId("TS");
        originalPlanet.setName("TestName");
        Planet saved = planetService.create(originalPlanet);
        String savedId = saved.getId();

        //When
        Planet expectedPlanet = planetService.getById(saved.getId());
        String expectedId = expectedPlanet.getId();

        //Then
        Assertions.assertEquals(expectedId, savedId);

    }

    @Test
    void updateTest() {

        //Given
        Planet originalPlanet = planetService.getById("TS");

        originalPlanet.setName("NewName");
        String originalName = originalPlanet.getName();
        String originalId = originalPlanet.getId();

        //When
        Planet updated = planetService.update(originalPlanet);
        String updatedName = updated.getName();
        String savedId = updated.getId();

        //Then
        Assertions.assertEquals(updatedName, originalName);
        Assertions.assertEquals(savedId, originalId);

    }

    @Test
    void deleteByIdTest() {

        //Given
        Planet originalPlanet = new Planet();
        originalPlanet.setId("TS");
        originalPlanet.setName("TestName");
        Planet saved = planetService.create(originalPlanet);

        //When
        String savedId = saved.getId();
        planetService.deleteById(savedId);

        //Then
        Assertions.assertNull(planetService.getById(savedId));

    }

}