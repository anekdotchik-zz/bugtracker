package org.test.bugtracker.impl.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.bugtracker.bo.BugBO;
import org.test.bugtracker.dao.BugDAO;
import org.test.bugtracker.model.Bug;

@Repository
public class BugBOImpl implements BugBO {
    private static final long serialVersionUID = -8499290975477476609L;
    
    @Autowired
    private BugDAO bugDAO;

    @Transactional
    public void save(Bug entity) {
        bugDAO.save(entity);
    }

    @Transactional
    public Bug findById(Long id) {
        return bugDAO.findById(id);
    }

    @Transactional
    public void update(Bug entity) {
        bugDAO.update(entity);
    }

    @Transactional
    public void delete(Bug entity) {
        bugDAO.delete(entity);
    }
}
