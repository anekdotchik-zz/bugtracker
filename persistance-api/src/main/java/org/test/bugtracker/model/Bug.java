package org.test.bugtracker.model;

import java.util.List;

public interface Bug extends SimpleIdentifiedEntity {
    User getAuthor();

    void setAuthor(User author);

    String getTitle();

    void setTitle(String title);

    String getMessage();

    void setMessage(String message);

    void setComments(List<Comment> comments);

    List<Comment> getComments();
}
