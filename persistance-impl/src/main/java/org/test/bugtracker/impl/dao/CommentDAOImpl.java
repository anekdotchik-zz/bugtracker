package org.test.bugtracker.impl.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.test.bugtracker.dao.CommentDAO;
import org.test.bugtracker.model.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
    private static final long serialVersionUID = -6220870787332598552L;

    @Autowired
    private SessionFactory sessionFactory;
    
    public void save(Comment entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    public Comment findById(Long id) {
        return (Comment) sessionFactory.getCurrentSession()
                .createQuery("from Comment c where c.id=?")
                .setLong(0, id).uniqueResult();
    }

    public void update(Comment entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(Comment entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

}
