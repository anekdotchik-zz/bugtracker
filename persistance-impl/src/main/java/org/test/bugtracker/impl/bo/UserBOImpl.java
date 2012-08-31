package org.test.bugtracker.impl.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.dao.UserDAO;
import org.test.bugtracker.model.User;

public class UserBOImpl implements UserBO {
    private static final long serialVersionUID = -5525144797577781512L;
    @Autowired
    private UserDAO userDAO;
    
    public void save(User entity) {
        userDAO.save(entity);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public User findByLogin(String login) {
        return userDAO.findByLogin(login);
    }
    
    public void update(User entity) {
        userDAO.update(entity);
    }

    public void delete(User entity) {
        userDAO.delete(entity);
    }
}
