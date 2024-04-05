package com.goit.ticket;

import com.goit.storage.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO service implements CRUD-operations for ticket entity (Ticket).
 */
public class TicketDaoServiceImpl implements TicketDaoService {

    @Override
    public Ticket create(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getFromPlanetId() == null || ticket.getToPlanetId() == null) {
            throw new NullPointerException("Client or planet can't be empty");
        }
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(ticket);
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
            return session.get(Ticket.class, ticket.getId());
        }
    }

    @Override
    public Ticket getById(Long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public Ticket update(Ticket ticket) {
        if (ticket.getClient() == null || ticket.getFromPlanetId() == null || ticket.getToPlanetId() == null) {
            throw new NullPointerException("Client or planet can't be empty");
        }
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(ticket);
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
            return session.get(Ticket.class, ticket.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket toRemove = session.get(Ticket.class, id);
            session.remove(toRemove);
            transaction.commit();
        }

    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }
}
