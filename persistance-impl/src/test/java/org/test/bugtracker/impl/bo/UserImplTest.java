package org.test.bugtracker.impl.bo;

import static junit.framework.Assert.*;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.User;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring/context.xml" })
public class UserImplTest extends AbstractTestNGSpringContextTests {
    private static final String PASS = "pass";
    private static final long ID = 1L;
    private static final String LOGIN = "login";
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserBO userBO;

    @Test(priority = 0)
    public void createNewUser() {
        User user = new UserImpl();
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userBO.save(user);
        assertTrue(user.getId() == ID);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser")
    public void findUserById() {
        User user = userBO.findById(1L);
        assertNotNull(user);
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASS, user.getPass());
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser")
    public void findUserByLogin() {
        User user = userBO.findByLogin(LOGIN);
        assertNotNull(user);
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASS, user.getPass());
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser",
            expectedExceptions = { ConstraintViolationException.class })
    public void createUserWithSomeId() {
        User user = new UserImpl();
        user.setId(1L);
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userBO.save(user);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser",
            expectedExceptions = { PropertyValueException.class })
    public void createUserWithoutLogin() {
        User user = new UserImpl();
        user.setPass(PASS);
        userBO.save(user);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser",
            expectedExceptions = { PropertyValueException.class })
    public void createUserWithoutPass() {
        User user = new UserImpl();
        user.setLogin(LOGIN);
        userBO.save(user);
    }
    
    @Test(priority = 1, dependsOnMethods = "createNewUser",
            expectedExceptions = { ConstraintViolationException.class })
    public void createUserWithSomeLogin() {
        User user = new UserImpl();
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userBO.save(user);
    }

    @Test(priority = 2, dependsOnMethods = "createNewUser")
    public void updateUserLoginAndPass() {
        User user = userBO.findById(1L);
        user.setLogin(LOGIN + "2");
        user.setPass(PASS + "2");
        userBO.update(user);
        User user2 = userBO.findById(1L);
        assertNotNull(user2);
        assertEquals(LOGIN + "2", user2.getLogin());
        assertEquals(PASS + "2", user2.getPass());
        assertTrue(ID == user2.getId());
    }

    @Test(priority = 3, dependsOnMethods = "createNewUser")
    public void deleteUser() {
        User user = userBO.findById(1L);
        userBO.delete(user);
        User user2 = userBO.findById(1L);
        assertNull(user2);
    }
}
