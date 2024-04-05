package com.goit.storage;

import com.goit.client.Client;
import com.goit.planet.Planet;
import com.goit.ticket.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil provides connection to database using Hibernate ORM.
 */
public class HibernateUtil {
    private static final String DB_URL = "jdbc:h2:./database";
    private static final HibernateUtil INSTANCE;
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    public HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
        new DbInitService().initDb(DB_URL);
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }

}
