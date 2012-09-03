package org.test.bugtracker.ejb;

import javax.ejb.Remote;

import org.test.bugtracker.model.Bug;

@Remote
public interface BugEJB extends CommonEJB<Bug> {

}
