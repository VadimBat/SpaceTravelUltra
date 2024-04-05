package com.goit.ticket;

import com.goit.client.Client;
import com.goit.client.ClientDaoService;
import com.goit.client.ClientDaoServiceImpl;
import com.goit.planet.Planet;
import com.goit.planet.PlanetDaoService;
import com.goit.planet.PlanetDaoServiceImpl;
import com.goit.storage.DbInitService;
import com.goit.storage.HibernateUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.*;

class TicketDaoServiceImplTest {
    private HibernateUtil connection;
    private TicketDaoServiceImpl ticketService;
    private DbInitService init;

    @BeforeEach
    void beforeEach() {
        final String DB_URL = "jdbc:h2:mem:database;DB_CLOSE_DELAY=-1";
        ticketService = new TicketDaoServiceImpl();
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
        List<Ticket> expectedClients = ticketService.getAll();

        //Then
        Assertions.assertEquals(expectedClients.size(), actualSize);

    }

    @Test
    void createTest() throws ParseException {

        //Given
        Ticket originalTicket = new Ticket();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2024-04-05 11:50:00");
        long time = date.getTime();
        originalTicket.setCreatedAt(new Timestamp(time));

        ClientDaoService clientService = new ClientDaoServiceImpl();
        Client client = clientService.getById(7L);
        originalTicket.setClient(client);

        PlanetDaoService planetService = new PlanetDaoServiceImpl();
        Planet planetFrom = planetService.getById("VNR");
        Planet planetTo = planetService.getById("MRS");
        originalTicket.setFromPlanetId(planetFrom);
        originalTicket.setToPlanetId(planetTo);


        //When
        Ticket expectedTicket = ticketService.create(originalTicket);

        //Then
        Assertions.assertEquals(expectedTicket.getCreatedAt(), originalTicket.getCreatedAt());
        Assertions.assertEquals(expectedTicket.getClient(), originalTicket.getClient());
        Assertions.assertEquals(expectedTicket.getFromPlanetId(), originalTicket.getFromPlanetId());
        Assertions.assertEquals(expectedTicket.getToPlanetId(), originalTicket.getToPlanetId());

    }

    @Test
    void createNullPlanetTest() throws ParseException {

        //Given
        Ticket originalTicket = new Ticket();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2024-04-05 11:50:00");
        long time = date.getTime();
        originalTicket.setCreatedAt(new Timestamp(time));

        ClientDaoService clientService = new ClientDaoServiceImpl();
        Client client = clientService.getById(7L);
        originalTicket.setClient(client);

        PlanetDaoService planetService = new PlanetDaoServiceImpl();
        Planet planetFrom = null;
        Planet planetTo = null;
        originalTicket.setFromPlanetId(planetFrom);
        originalTicket.setToPlanetId(planetTo);


        //When & Then
        Assertions.assertThrows(NullPointerException.class, () -> ticketService.create(originalTicket));

    }

    @Test
    void createNullClientTest() throws ParseException {

        //Given
        Ticket originalTicket = new Ticket();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2024-04-05 11:50:00");
        long time = date.getTime();
        originalTicket.setCreatedAt(new Timestamp(time));

        ClientDaoService clientService = new ClientDaoServiceImpl();
        Client client = null;
        originalTicket.setClient(client);

        PlanetDaoService planetService = new PlanetDaoServiceImpl();
        Planet planetFrom = planetService.getById("VNR");
        Planet planetTo = planetService.getById("MRS");
        originalTicket.setFromPlanetId(planetFrom);
        originalTicket.setToPlanetId(planetTo);


        //When & Then
        Assertions.assertThrows(NullPointerException.class, () -> ticketService.create(originalTicket));

    }

    @Test
    void getByIdTest() throws ParseException {

        //Given
        Ticket originalTicket = new Ticket();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2024-04-05 12:50:00");
        long time = date.getTime();
        originalTicket.setCreatedAt(new Timestamp(time));

        ClientDaoService clientService = new ClientDaoServiceImpl();
        Client client = clientService.getById(7L);
        originalTicket.setClient(client);

        PlanetDaoService planetService = new PlanetDaoServiceImpl();
        Planet planetFrom = planetService.getById("MRK");
        Planet planetTo = planetService.getById("MRS");
        originalTicket.setFromPlanetId(planetFrom);
        originalTicket.setToPlanetId(planetTo);
        Ticket savedTicket = ticketService.create(originalTicket);
        Long savedId = savedTicket.getId();

        //When
        Ticket expectedTicket = ticketService.getById(savedTicket.getId());
        Long expectedId = expectedTicket.getId();

        //Then
        Assertions.assertEquals(expectedId, savedId);

    }

    @Test
    void updateTest() {

        //Given
        Ticket originalTicket = ticketService.getById(5L);

        Planet planetTo = new Planet();
        planetTo.setId("ETH");
        planetTo.setName("EARTH");
        originalTicket.setToPlanetId(planetTo);

        Planet originalToPlanet = originalTicket.getToPlanetId();
        Long originalId = originalTicket.getId();

        //When
        Ticket updated = ticketService.update(originalTicket);
        Planet updatedToPlanet = updated.getToPlanetId();
        Long savedId = updated.getId();

        //Then
        Assertions.assertEquals(updatedToPlanet, originalToPlanet);
        Assertions.assertEquals(savedId, originalId);

    }

    @Test
    void deleteByIdTest() throws ParseException {

        //Given
        Ticket originalTicket = new Ticket();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2024-04-05 13:10:00");
        long time = date.getTime();
        originalTicket.setCreatedAt(new Timestamp(time));

        ClientDaoService clientService = new ClientDaoServiceImpl();
        Client client = clientService.getById(7L);
        originalTicket.setClient(client);

        PlanetDaoService planetService = new PlanetDaoServiceImpl();
        Planet planetFrom = planetService.getById("ETH");
        Planet planetTo = planetService.getById("VNR");
        originalTicket.setFromPlanetId(planetFrom);
        originalTicket.setToPlanetId(planetTo);

        Ticket savedTicket = ticketService.create(originalTicket);

        //When
        Long savedId = savedTicket.getId();
        ticketService.deleteById(savedId);

        //Then
        Assertions.assertNull(ticketService.getById(savedId));

    }


}