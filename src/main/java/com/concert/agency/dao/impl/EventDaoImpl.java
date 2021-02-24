package com.concert.agency.dao.impl;

import com.concert.agency.dao.EventDao;
import com.concert.agency.exception.DataProcessingException;
import com.concert.agency.model.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<Event> implements EventDao {
    @Autowired
    public EventDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Event> findAvailableEvents(Long performanceId, LocalDate date) {
        LocalDateTime fromDateTime = LocalDateTime.of(date, LocalTime.MIN);
        LocalDateTime toDateTime = LocalDateTime.of(date, LocalTime.MAX);
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Event e "
                    + "INNER JOIN FETCH e.performance AS p "
                    + "INNER JOIN FETCH e.concertHall "
                    + "WHERE p.id=:performanceId AND e.showTime BETWEEN :from AND :to", Event.class)
                    .setParameter("performanceId", performanceId).setParameter("from", fromDateTime)
                    .setParameter("to", toDateTime).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to get available Performances from DB", e);
        }
    }

    @Override
    public void update(Event event) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to update " + event, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeById(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Event e WHERE e.id=:id")
            .setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Unable to remove Performance with id=" + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Event> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Event WHERE id=:id", Event.class)
                    .setParameter("id", id).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Unable to find Performance with id=" + id, e);
        }
    }
}
