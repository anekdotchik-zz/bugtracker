package org.test.bugtracker.bo;

import org.test.bugtracker.model.BTUser;

public interface BTUserBO extends CommonBO<BTUser> {
    public BTUser findByLogin(String login);
}
