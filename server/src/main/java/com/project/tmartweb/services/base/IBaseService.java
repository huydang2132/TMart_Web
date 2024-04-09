package com.project.tmartweb.services.base;

import java.util.UUID;

public interface IBaseService<Entity, EntityDTO, DataType> extends IBaseServiceReadOnly<Entity, DataType> {
    Entity insert(EntityDTO entityDTO);

    Entity update(DataType id, EntityDTO entityDTO);

    void delete(Entity entity);
}
