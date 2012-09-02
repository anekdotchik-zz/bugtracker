package org.test.bugtracker.impl.bo;

import static junit.framework.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.test.bugtracker.bo.BugBO;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.impl.model.BugImpl;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.Bug;
import org.test.bugtracker.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring/context.xml" })
public class BugImplTest extends AbstractTestNGSpringContextTests {
    private static final String USER_NAME = BugImplTest.class.getSimpleName();

    @Autowired
    private ApplicationContext context;
    @Autowired
    private BugBO bugBO;
    @Autowired
    private UserBO userBO;

    private User user;
    
    @BeforeClass
    private void setup() {
        user = new UserImpl();
        user.setLogin(USER_NAME);
        user.setPass(USER_NAME);
        userBO.save(user);
    }

    @Test(priority = 0)
    public void createNewBug() {
        Bug bug = new BugImpl();
        bug.setTitle("title");
        bug.setMessage("message");
        bug.setAuthor(user);
        bugBO.save(bug);
        assertNotNull(bug.getId());
        Bug bug1 = bugBO.findById(bug.getId());
        assertEquals("title",bug1.getTitle());
        assertEquals("message",bug1.getMessage());
    }

}
