package org.test.bugtracker.ejb;

import static org.testng.Assert.*;

import javax.ejb.EJBException;
import javax.naming.NamingException;

import org.test.bugtracker.impl.model.BugImpl;
import org.test.bugtracker.impl.model.CommentImpl;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.Bug;
import org.test.bugtracker.model.Comment;
import org.test.bugtracker.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CommentEJBTest extends CommonEJBTest {
    private User user;
    private Bug bug;

    @BeforeClass
    private void setup() throws NamingException {
        super.init();

        user = new UserImpl();
        user.setLogin(LOGIN);
        user.setPass("pass");
        userEJB.save(user);
        assertNotNull(user.getId());
        bug = new BugImpl();
        bug.setTitle("title");
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        bugEJB.save(bug);
        assertNotNull(bug.getId());
    }

    @BeforeMethod
    public void cleanupComments() {
        bug.setComments(null);
        bugEJB.save(bug);
    }

    @Test
    public void createNewComment() {
        Comment comment = new CommentImpl();
        comment.setMessage(MESSAGE);
        comment.setAuthor(user);
        comment.setBug(bug);
        commentEJB.save(comment);
        assertNotNull(comment.getId());
        Comment comment2 = commentEJB.findById(comment.getId());
        assertNotNull(comment2);
        assertEquals(MESSAGE, comment2.getMessage());
    }

    @Test(expectedExceptions = { EJBException.class })
    public void createNewCommentWithoutAuthor() {
        Comment comment = new CommentImpl();
        comment.setMessage(MESSAGE);
        comment.setBug(bug);
        commentEJB.save(comment);
    }

    @Test(expectedExceptions = { EJBException.class })
    public void createNewCommentWithoutBug() {
        Comment comment = new CommentImpl();
        comment.setMessage(MESSAGE);
        comment.setAuthor(user);
        commentEJB.save(comment);
    }

    @Test(expectedExceptions = { EJBException.class })
    public void createNewCommentWithoutMessage() {
        Comment comment = new CommentImpl();
        comment.setBug(bug);
        comment.setAuthor(user);
        commentEJB.save(comment);
    }

    @Test
    public void updateComment() {
        Comment comment = new CommentImpl();
        comment.setMessage(MESSAGE);
        comment.setAuthor(user);
        comment.setBug(bug);
        commentEJB.save(comment);
        Long id = comment.getId();
        assertNotNull(id);
        Comment comment2 = commentEJB.findById(id);
        assertNotNull(comment2);
        assertEquals(MESSAGE, comment2.getMessage());
        String message2 = MESSAGE + "2";
        comment2.setMessage(message2);
        commentEJB.update(comment2);
        Comment comment3 = commentEJB.findById(id);
        assertNotNull(comment3);
        assertEquals(message2, comment3.getMessage());

    }

    @Test()
    public void deleteComment() {
        Comment comment = new CommentImpl();
        comment.setMessage(MESSAGE);
        comment.setAuthor(user);
        comment.setBug(bug);
        commentEJB.save(comment);
        Long id = comment.getId();
        assertNotNull(id);
        Comment comment2 = commentEJB.findById(id);
        assertEquals(MESSAGE, comment2.getMessage());
        commentEJB.delete(comment2);
        assertNull(commentEJB.findById(id));
    }
}
