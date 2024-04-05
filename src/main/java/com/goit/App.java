package com.goit;

import com.goit.client.Client;
import com.goit.client.ClientDaoService;
import com.goit.client.ClientDaoServiceImpl;
import com.goit.planet.Planet;
import com.goit.planet.PlanetDaoService;
import com.goit.planet.PlanetDaoServiceImpl;
import com.goit.storage.HibernateUtil;
import com.goit.ticket.Ticket;
import com.goit.ticket.TicketDaoService;
import com.goit.ticket.TicketDaoServiceImpl;

import java.util.List;


public class App {
    public static void main(String[] args) {

        //Connection and creation database
        HibernateUtil connect = HibernateUtil.getInstance();

        //ClientDaoService
        ClientDaoService clientService = new ClientDaoServiceImpl();

        Client clientNew = new Client();
        clientNew.setName("Zelimhan");
        System.out.println(clientService.create(clientNew));

        System.out.println(clientService.getById(3L));

        List<Client> all = clientService.getAll();
        all.forEach(client -> System.out.println("allClients = " + client));

        //PlanetDaoService
        PlanetDaoService planetService = new PlanetDaoServiceImpl();

        System.out.println(planetService.getById("MRS"));

        List<Planet> allPlanets = planetService.getAll();
        allPlanets.forEach(planet -> System.out.println("allPlanets = " + planet));

        //TicketDaoService
        TicketDaoService ticketService = new TicketDaoServiceImpl();

        System.out.println(ticketService.getById(3L));

        List<Ticket> allTickets = ticketService.getAll();
        allTickets.forEach(ticket -> System.out.println("allTickets = " + ticket));

        //Close connection
        connect.close();

    }
}