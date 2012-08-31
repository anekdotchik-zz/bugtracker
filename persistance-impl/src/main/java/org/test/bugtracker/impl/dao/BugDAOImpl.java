package org.test.bugtracker.impl.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.test.bugtracker.dao.BugDAO;
import org.test.bugtracker.impl.model.BugImpl;
import org.test.bugtracker.model.Bug;

@Repository
public class BugDAOImpl implements BugDAO {
    private static final long serialVersionUID = 4680256623344508261L;

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Bug entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public Bug findById(Long id) {
        return (Bug) sessionFactory.getCurrentSession().load(BugImpl.class, id);
    }

    public void update(Bug entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(Bug entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

}
