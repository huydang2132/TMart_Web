package com.project.tmartweb.services.base;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBaseServiceReadOnly<Entity, DataType> {
    List<Entity> getAll();

    Optional<Entity> findById(DataType id);

    Entity getById(DataType id);
}
