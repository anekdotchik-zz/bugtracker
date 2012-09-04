package org.test.bugtracker.ejb;


import org.test.bugtracker.model.User;

public interface UserEJB extends CommonEJB<User> {
    public User findByLogin(String login);
}
