package org.test.bugtracker.impl.bo;

import org.test.bugtracker.bo.BugBO;
import org.test.bugtracker.dao.BugDAO;
import org.test.bugtracker.model.Bug;

public class BugBOImpl implements BugBO {
    private static final long serialVersionUID = -8499290975477476609L;

    private BugDAO bugDAO;
    
    public void setBugDAO(BugDAO bugDAO) {
        this.bugDAO = bugDAO;
    }

    public void save(Bug entity) {
        bugDAO.save(entity);
    }

    public Bug findById(Long id) {
        return bugDAO.findById(id);
    }

    public void update(Bug entity) {
        bugDAO.update(entity);
    }

    public void delete(Bug entity) {
        bugDAO.delete(entity);
    }
}