package org.test.bugtracker.model;

public interface Comment extends SimpleIdentifiedEntity {
    Bug getBug();

    void setBug(Bug bug);

    User getAuthor();

    void setAuthor(User author);

    String getMessage();

    void setMessage(String message);
}
