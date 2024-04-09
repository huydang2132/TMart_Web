package com.project.tmartweb.services;

import com.project.tmartweb.services.base.IBaseService;
import com.project.tmartweb.models.dtos.CategoryDTO;
import com.project.tmartweb.models.entities.Category;

import java.util.UUID;

public interface ICategoryService extends IBaseService<Category, CategoryDTO, UUID> {
}
