package org.test.bugtracker.model;

import java.io.Serializable;

public interface IdentifiedEntity<C extends Serializable> {
    C getId();
    void setId(C id);
}
