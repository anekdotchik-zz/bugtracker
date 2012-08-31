package org.test.bugtracker.model;

public interface User extends SimpleIdentifiedEntity {
    String getLogin();

    void setLogin(String login);

    String getPass();

    void setPass(String pass);
}
