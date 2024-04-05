package com.goit.client;

import com.goit.storage.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * DAO service implements CRUD-operations for client entity (Client).
 */
public class ClientDaoServiceImpl implements ClientDaoService {
    @Override
    public Client create(Client client) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(client);
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
            return session.get(Client.class, client.getId());
        }
    }

    @Override
    public Client getById(Long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        }
    }

    @Override
    public Client update(Client client) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(client);
                transaction.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                transaction.rollback();
            }
            return session.get(Client.class, client.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client toRemove = session.get(Client.class, id);
            session.remove(toRemove);
            transaction.commit();
        }
    }

    @Override
    public List<Client> getAll() {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }
}
