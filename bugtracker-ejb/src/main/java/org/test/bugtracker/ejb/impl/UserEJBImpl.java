package org.test.bugtracker.ejb.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;
import org.test.bugtracker.bo.UserBO;
import org.test.bugtracker.ejb.UserEJB;
import org.test.bugtracker.model.User;

@Stateless(name="UserEJB")
@Local(value={UserEJB.class})
@Remote(value={UserEJB.class})
@Interceptors(SpringBeanAutowiringInterceptor.class)
public class UserEJBImpl implements UserEJB {
    private static final long serialVersionUID = -7673799065009967650L;
    @Autowired
    private UserBO userBO;
    
    public void save(User entity) {
        userBO.save(entity);
    }

    public User findById(Long id) {
        return userBO.findById(id);
    }

    public User findByLogin(String login) {
        return userBO.findByLogin(login);
    }
    
    public void update(User entity) {
        userBO.update(entity);
    }

    public void delete(User entity) {
        userBO.delete(entity);
    }
}
