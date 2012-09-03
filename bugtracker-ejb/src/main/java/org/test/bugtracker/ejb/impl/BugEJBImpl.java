package org.test.bugtracker.ejb.impl;

import javax.ejb.Stateless;

import org.springframework.beans.factory.annotation.Autowired;
import org.test.bugtracker.bo.BugBO;
import org.test.bugtracker.ejb.BugEJB;
import org.test.bugtracker.model.Bug;

@Stateless
public class BugEJBImpl implements BugEJB {
    private static final long serialVersionUID = -2442261509206116336L;
    @Autowired
    private BugBO bugBO;

    public void save(Bug entity) {
        bugBO.save(entity);
    }

    public Bug findById(Long id) {
        return bugBO.findById(id);
    }

    public void update(Bug entity) {
        bugBO.update(entity);
    }

    public void delete(Bug entity) {
        bugBO.delete(entity);
    }
}
