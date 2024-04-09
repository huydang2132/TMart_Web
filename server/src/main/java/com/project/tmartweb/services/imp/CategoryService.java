package com.project.tmartweb.services.imp;

import com.project.tmartweb.repositories.CategoryRepository;
import com.project.tmartweb.services.ICategoryService;
import com.project.tmartweb.exceptions.DataConflictException;
import com.project.tmartweb.exceptions.DataNotFoundException;
import com.project.tmartweb.models.dtos.CategoryDTO;
import com.project.tmartweb.models.entities.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public Category insert(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            throw new DataConflictException("Category name already exists");
        }
        Category category = mapper.map(categoryDTO, Category.class);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UUID id, CategoryDTO categoryDTO) {
        Category category = getById(id);
        mapper.map(categoryDTO, category);
        category.setUpdatedBy(categoryDTO.getUpdatedBy());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category getById(UUID id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
    }
}
