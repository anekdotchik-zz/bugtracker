package org.test.bugtracker.impl.bo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/context.xml"})
public class UserImplTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserBO userBO;

    @Test
    public void createNewUserTest() {
        User user = new UserImpl();
        user.setLogin("login");
        user.setPass("pass");
        userBO.save(user);
    }
}
