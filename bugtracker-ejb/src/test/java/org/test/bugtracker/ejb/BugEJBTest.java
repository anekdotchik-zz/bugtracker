package org.test.bugtracker.ejb;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.hibernate.PropertyValueException;
import org.test.bugtracker.impl.model.BugImpl;
import org.test.bugtracker.impl.model.CommentImpl;
import org.test.bugtracker.impl.model.UserImpl;
import org.test.bugtracker.model.Bug;
import org.test.bugtracker.model.Comment;
import org.test.bugtracker.model.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BugEJBTest extends CommonEJBTest {
    private static final String TITLE = "title";
    
    private User user;

    @BeforeClass
    private void setup() throws NamingException {
        user = new UserImpl();
        user.setLogin(LOGIN);
        user.setPass(PASS);
        userEJB.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void createNewBug() {
        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        bugEJB.save(bug);
        assertNotNull(bug.getId());
        Bug bug2 = bugEJB.findById(bug.getId());
        assertEquals(TITLE, bug2.getTitle());
        assertEquals(MESSAGE, bug2.getMessage());
    }

    @Test(expectedExceptions = { PropertyValueException.class })
    public void createNewBugWithoutAuthor() {
        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setMessage(MESSAGE);
        bugEJB.save(bug);
    }

    @Test
    public void createNewBugWithEmptyComments() {
        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        bug.setComments(new ArrayList<Comment>());
        bugEJB.save(bug);
        assertNotNull(bug.getId());
        Bug bug2 = bugEJB.findById(bug.getId());
        assertEquals(TITLE, bug2.getTitle());
        assertEquals(MESSAGE, bug2.getMessage());
    }

    @Test
    public void createNewBugWithNotEmptyComments() {
        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        List<Comment> comments = new ArrayList<Comment>();
        Comment comment = new CommentImpl();
        comment.setAuthor(user);
        comment.setMessage(MESSAGE);
        comment.setBug(bug);
        comments.add(comment);
        bug.setComments(comments);
        bugEJB.save(bug);
        Long id = bug.getId();
        assertNotNull(id);
        Bug bug2 = bugEJB.findById(id);
        assertEquals(TITLE, bug2.getTitle());
        assertEquals(MESSAGE, bug2.getMessage());
        assertEquals(user, bug2.getAuthor());
        List<Comment> comments2 = bug2.getComments();
        assertNotNull(comments2);
        assertEquals(1, comments2.size());
        assertEquals(comment, comments2.get(0));
    }

    @Test
    public void updateBug() {
        String title2 = "title2";
        String message2 = "message2";

        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        bugEJB.save(bug);
        Long id = bug.getId();
        assertNotNull(id);
        Bug bug2 = bugEJB.findById(id);
        assertEquals(TITLE, bug2.getTitle());
        assertEquals(MESSAGE, bug2.getMessage());
        bug2.setTitle(title2);
        bug2.setMessage(message2);
        bugEJB.update(bug2);
        Long id2 = bug2.getId();
        assertEquals(bug.getId(), id2);
        Bug bug3 = bugEJB.findById(id2);
        assertNotNull(bug3);
        assertEquals(title2, bug3.getTitle());
        assertEquals(message2, bug3.getMessage());
    }

    @Test(expectedExceptions = { PropertyValueException.class })
    public void createNewBugWithoutTitle() {
        Bug bug = new BugImpl();
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        bugEJB.save(bug);
        assertNotNull(bug.getId());
    }

    @Test(expectedExceptions = { PropertyValueException.class })
    public void createNewBugWithoutMessage() {
        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setAuthor(user);
        bugEJB.save(bug);
    }

    @Test
    public void deleteBug() {
        Bug bug = new BugImpl();
        bug.setTitle(TITLE);
        bug.setMessage(MESSAGE);
        bug.setAuthor(user);
        bugEJB.save(bug);
        Long id = bug.getId();
        assertNotNull(id);
        Bug bug2 = bugEJB.findById(id);
        assertEquals(TITLE, bug2.getTitle());
        assertEquals(MESSAGE, bug2.getMessage());
        bugEJB.delete(bug2);
        assertNull(bugEJB.findById(id));
    }
}
