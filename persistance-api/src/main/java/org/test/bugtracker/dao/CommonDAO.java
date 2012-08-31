package org.test.bugtracker.dao;

import java.io.Serializable;

import org.test.bugtracker.model.SimpleIdentifiedEntity;

public interface CommonDAO<C extends SimpleIdentifiedEntity> extends
        Serializable {
    void save(C entity);

    C findById(Long id);

    void update(C entity);

    void delete(C entity);
}
