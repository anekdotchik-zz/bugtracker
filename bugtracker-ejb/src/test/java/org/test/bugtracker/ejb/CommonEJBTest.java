package org.test.bugtracker.ejb;

import static org.testng.Assert.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

public class CommonEJBTest {
    protected final String LOGIN = this.getClass().getSimpleName();
    protected static final String PASS = "pass";
    protected static final String MESSAGE = "message";
    protected static EJBContainer ejbContainer;
    protected static Context ctx;
    protected static BugEJB bugEJB;
    protected static UserEJB userEJB;
    protected static CommentEJB commentEJB;
    private static boolean isInit = false;

    private static void commonSetup() throws NamingException {
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

//    @AfterSuite
//    private void commonCleanup() {
//        ejbContainer.close();
//    }

    
    protected void init() throws NamingException {
        if (!isInit) {
            commonSetup();
            isInit = true;
        }
    }
}
