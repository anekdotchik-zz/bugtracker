package org.test.bugtracker.ejb;


import javax.ejb.Local;

import org.test.bugtracker.model.User;

@Local
public interface UserEJB extends CommonEJB<User> {
    public User findByLogin(String login);
}
