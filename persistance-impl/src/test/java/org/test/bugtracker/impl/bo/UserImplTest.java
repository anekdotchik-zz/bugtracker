package org.test.bugtracker.impl.bo;

import static junit.framework.Assert.*;

import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.User;

@ContextConfiguration(locations = { "classpath:spring/context.xml" })
public class UserImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserBO userBO;

    @Test(priority=0)
    public void createNewUserTest() {
        User user = new UserImpl();
        user.setLogin("login");
        user.setPass("pass");
        userBO.save(user);
        assertTrue(user.getId() == 1L);
    }
    
    @Test(dependsOnMethods="createNewUserTest")
    public void findUserTestById() {
        User user = userBO.findById(1L);
        assertNotNull(user);
        assertEquals("login", user.getLogin());
        assertEquals("pass", user.getPass());
    }
    
    @Test(dependsOnMethods="createNewUserTest")
    public void findUserTestByLogin() {
        User user = userBO.findByLogin("login");
        assertNotNull(user);
        assertEquals("login", user.getLogin());
        assertEquals("pass", user.getPass());
    }    
}
