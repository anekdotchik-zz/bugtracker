package org.test.bugtracker.model;

public interface Comment extends SimpleIdentifiedEntity {
    Bug getBug();

    void setBug(Bug bug);

    BTUser getAuthor();

    void setAuthor(BTUser author);

    String getMessage();

    void setMessage(String message);
}
