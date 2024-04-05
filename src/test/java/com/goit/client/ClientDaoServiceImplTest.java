package com.goit.client;

import com.goit.storage.DbInitService;
import com.goit.storage.HibernateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClientDaoServiceImplTest {

    private HibernateUtil connection;
    private ClientDaoServiceImpl clientService;
    private DbInitService init;

    @BeforeEach
    void beforeEach() {
        final String DB_URL = "jdbc:h2:mem:database;DB_CLOSE_DELAY=-1";
        clientService = new ClientDaoServiceImpl();
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
        int actualSize = 10;

        //When
        List<Client> expectedClients = clientService.getAll();

        //Then
        Assertions.assertEquals(expectedClients.size(), actualSize);

    }

    @Test
    void createTest() {

        //Given
        Client originalClient = new Client();
        originalClient.setName("TestName");
        String originalName = originalClient.getName();

        //When
        Client expectedClient = clientService.create(originalClient);
        String expectedName = expectedClient.getName();

        //Then
        Assertions.assertEquals(expectedName, originalName);

    }

    @Test
    void getByIdTest() {

        //Given
        Client originalClient = new Client();
        originalClient.setName("TestName");
        Client saved = clientService.create(originalClient);
        Long savedId = saved.getId();

        //When
        Client expectedClient = clientService.getById(saved.getId());
        Long expectedId = expectedClient.getId();

        //Then
        Assertions.assertEquals(expectedId, savedId);
    }

    @Test
    void updateTest() {

        //Given
        Client originalClient = clientService.getById(9L);
        originalClient.setName("NewName");
        String originalName = originalClient.getName();
        Long originalId = originalClient.getId();

        //When
        Client updated = clientService.update(originalClient);
        String updatedName = updated.getName();
        Long savedId = updated.getId();

        //Then
        Assertions.assertEquals(updatedName, originalName);
        Assertions.assertEquals(savedId, originalId);

    }

    @Test
    void deleteByIdTest() {

        //Given
        Client originalClient = new Client();
        originalClient.setName("TestName");
        Client saved = clientService.create(originalClient);

        //When
        Long savedId = saved.getId();
        clientService.deleteById(savedId);

        //Then
        Assertions.assertNull(clientService.getById(savedId));

    }

}