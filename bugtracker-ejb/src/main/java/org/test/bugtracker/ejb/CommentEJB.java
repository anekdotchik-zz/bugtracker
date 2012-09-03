package org.test.bugtracker.ejb;


import javax.ejb.Remote;

import org.test.bugtracker.model.Comment;

@Remote
public interface CommentEJB extends CommonEJB<Comment> {

}
