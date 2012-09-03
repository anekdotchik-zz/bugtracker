package org.test.bugtracker.ejb;

import static org.testng.Assert.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:spring/context.xml" })
public class UserEJBTest extends AbstractTestNGSpringContextTests {
    private static final String LOGIN = "login";
    private static final String PASS = "pass";

    private static EJBContainer ejbContainer;
    private static Context ctx;

    private UserEJB userEJB;
    
    @BeforeClass
    public static void setUp() {
        ejbContainer = EJBContainer.createEJBContainer();
        ctx = ejbContainer.getContext();
    }

    @AfterClass
    public static void tearDown() {
        ejbContainer.close();
    }

    @BeforeMethod
    public void init() throws NamingException {
        userEJB = (UserEJB) ctx.lookup("java:global/classes/UserEJB!org.test.bugtracker.ejb.UserEJB");
//        userEJB = new UserEJBImpl();
        assertNotNull(userEJB);
    }
    
    @Test(priority = 0)
    public void createNewUser() {
        User user = new UserImpl();
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userEJB.save(user);
        assertTrue(user.getId() > 0);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser")
    public void findUserByLogin() {
        User user = userEJB.findByLogin(LOGIN);
        assertNotNull(user);
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASS, user.getPass());

        user = userEJB.findById(user.getId());
        assertNotNull(user);
        assertEquals(LOGIN, user.getLogin());
        assertEquals(PASS, user.getPass());
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser", expectedExceptions = { ConstraintViolationException.class })
    public void createUserWithSomeId() {
        User user = new UserImpl();
        user.setId(1L);
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userEJB.save(user);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser", expectedExceptions = { PropertyValueException.class })
    public void createUserWithoutLogin() {
        User user = new UserImpl();
        user.setPass(PASS);
        userEJB.save(user);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser", expectedExceptions = { PropertyValueException.class })
    public void createUserWithoutPass() {
        User user = new UserImpl();
        user.setLogin(LOGIN);
        userEJB.save(user);
    }

    @Test(priority = 1, dependsOnMethods = "createNewUser", expectedExceptions = { ConstraintViolationException.class })
    public void createUserWithSomeLogin() {
        User user = new UserImpl();
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userEJB.save(user);
    }

    @Test(priority = 2, dependsOnMethods = "createNewUser")
    public void updateUserLoginAndPass() {
        String newLogin = LOGIN + "2";
        String newPass = PASS + "2";
        User user = userEJB.findByLogin(LOGIN);
        long id = user.getId();
        assertNotNull(user);
        user.setLogin(newLogin);
        user.setPass(newPass);
        userEJB.update(user);
        User user2 = userEJB.findByLogin(newLogin);
        assertNotNull(user2);
        assertEquals(newLogin, user2.getLogin());
        assertEquals(newPass, user2.getPass());
        assertTrue(id == user2.getId());
        User user3 = userEJB.findByLogin(newLogin);
        assertNotNull(user3);
        user3.setLogin(LOGIN);
        user3.setPass(PASS);
        userEJB.update(user3);
        User user4 = userEJB.findByLogin(LOGIN);
        assertTrue(id == user4.getId());
        assertEquals(LOGIN, user4.getLogin());
        assertEquals(PASS, user4.getPass());

    }

    @Test(priority = 3, dependsOnMethods = "updateUserLoginAndPass")
    public void deleteUser() {
        User user = userEJB.findByLogin(LOGIN);
        userEJB.delete(user);
        User user2 = userEJB.findByLogin(LOGIN);
        assertNull(user2);
    }
}
