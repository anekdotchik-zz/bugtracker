package org.test.bugtracker.bo;

import java.io.Serializable;

import org.test.bugtracker.model.SimpleIdentifiedEntity;

public interface CommonBO<C extends SimpleIdentifiedEntity> extends
        Serializable {
    void save(C entity);

    C findById(Long id);

    void update(C entity);

    void delete(C entity);
}
