package org.test.bugtracker.impl.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.dao.UserDAO;
import org.test.bugtracker.model.User;

@Repository
public class UserBOImpl implements UserBO {
    private static final long serialVersionUID = -5525144797577781512L;
    
    @Autowired
    private UserDAO userDAO;
    
    @Transactional
    public void save(User entity) {
        userDAO.save(entity);
    }

    @Transactional
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Transactional
    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
    
    @Transactional
    public void update(User entity) {
        userDAO.update(entity);
    }

    @Transactional
    public void delete(User entity) {
        userDAO.delete(entity);
    }
}
