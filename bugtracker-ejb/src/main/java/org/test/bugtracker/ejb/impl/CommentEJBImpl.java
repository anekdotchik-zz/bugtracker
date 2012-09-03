package org.test.bugtracker.ejb.impl;

import javax.ejb.Stateless;

import org.springframework.beans.factory.annotation.Autowired;
import org.test.bugtracker.bo.CommentBO;
import org.test.bugtracker.ejb.CommentEJB;
import org.test.bugtracker.model.Comment;

@Stateless
public class CommentEJBImpl implements CommentEJB {
    private static final long serialVersionUID = -1391012741452615939L;
    @Autowired
    private CommentBO commentBO;

    public void save(Comment entity) {
        commentBO.save(entity);
    }

    public Comment findById(Long id) {
        return commentBO.findById(id);
    }

    public void update(Comment entity) {
        commentBO.update(entity);
    }
    
    public void delete(Comment entity) {
        commentBO.delete(entity);
    }
}
