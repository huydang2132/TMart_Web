package com.project.tmartweb.services.base;

import java.util.List;

public interface IBaseServiceMultiple<Entity, EntityDTO> {
    List<Entity> insertMultiple(List<EntityDTO> dtos);

    List<Entity> updateMultiple(List<EntityDTO> dtos);

    List<Entity> deleteMultiple(List<Entity> entities);
}
