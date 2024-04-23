package com.project.tmartweb.services.category;

import com.project.tmartweb.domain.dtos.CategoryDTO;
import com.project.tmartweb.domain.entities.Category;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface ICategoryService extends IBaseService<Category, CategoryDTO, UUID> {
}
