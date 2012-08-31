package org.test.bugtracker.bo;

import org.test.bugtracker.model.User;

public interface UserBO extends CommonBO<User> {
    public User findByLogin(String login);
}
