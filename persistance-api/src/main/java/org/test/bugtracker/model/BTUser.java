package org.test.bugtracker.model;

public interface BTUser extends SimpleIdentifiedEntity {
    String getLogin();

    void setLogin(String login);

    String getPass();

    void setPass(String pass);
}
