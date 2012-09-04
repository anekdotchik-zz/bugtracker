package org.test.bugtracker.ejb;

import static org.testng.Assert.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class CommonEJBTest {
    protected final String LOGIN = this.getClass().getSimpleName();
    protected static final String PASS = "pass";
    protected static final String MESSAGE = "message";
    protected EJBContainer ejbContainer;
    protected Context ctx;
    protected BugEJB bugEJB;
    protected UserEJB userEJB;
    protected CommentEJB commentEJB;

    @BeforeSuite
    private void commonSetup() throws NamingException {
        ejbContainer = EJBContainer.createEJBContainer();
        ctx = ejbContainer.getContext();

        userEJB = (UserEJB) ctx
                .lookup("java:global/classes/UserEJB!org.test.bugtracker.ejb.UserEJB");
        assertNotNull(userEJB);
        bugEJB = (BugEJB) ctx
                .lookup("java:global/classes/BugEJB!org.test.bugtracker.ejb.BugEJB");
        assertNotNull(bugEJB);
        commentEJB = (CommentEJB) ctx
                .lookup("java:global/classes/CommentEJB!org.test.bugtracker.ejb.CommentEJB");
        assertNotNull(userEJB);
    }

    @AfterSuite
    private void commonCleanup() {
        ejbContainer.close();
    }

}
