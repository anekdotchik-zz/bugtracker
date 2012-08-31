package org.test.bugtracker.dao;

import org.test.bugtracker.model.BTUser;

public interface BTUserDAO extends CommonDAO<BTUser> {
    public BTUser findByLogin(String login);
}
