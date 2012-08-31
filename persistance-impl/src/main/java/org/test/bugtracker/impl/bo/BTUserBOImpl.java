package org.test.bugtracker.impl.bo;

import org.test.bugtracker.bo.BTUserBO;
import org.test.bugtracker.dao.BTUserDAO;
import org.test.bugtracker.model.BTUser;

public class BTUserBOImpl implements BTUserBO {
    private static final long serialVersionUID = -5525144797577781512L;

    private BTUserDAO btUserDAO;
    
    public void setBtUserDAO(BTUserDAO btUserDAO) {
        this.btUserDAO = btUserDAO;
    }

    public void save(BTUser entity) {
        btUserDAO.save(entity);
    }

    public BTUser findById(Long id) {
        return btUserDAO.findById(id);
    }

    public BTUser findByLogin(String login) {
        return btUserDAO.findByLogin(login);
    }
    
    public void update(BTUser entity) {
        btUserDAO.update(entity);
    }

    public void delete(BTUser entity) {
        btUserDAO.delete(entity);
    }
}
