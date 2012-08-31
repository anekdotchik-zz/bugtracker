package org.test.bugtracker.dao;

import org.test.bugtracker.model.User;

public interface UserDAO extends CommonDAO<User> {
    public User findByLogin(String login);
}
