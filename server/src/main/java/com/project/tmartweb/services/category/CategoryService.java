package com.project.tmartweb.services.category;

import com.project.tmartweb.domain.dtos.CategoryDTO;
import com.project.tmartweb.domain.entities.Category;
import com.project.tmartweb.domain.paginate.BasePagination;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.exceptions.ConflictException;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
            throw new ConflictException("Category name already exists");
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
    public PaginationDTO<Category> getAll(Integer page, Integer perPage) {
        if (page == null && perPage == null) {
            return new PaginationDTO<>(categoryRepository.findAll(), null);
        }
        BasePagination<Category, CategoryRepository> basePagination = new BasePagination<>(categoryRepository);
        PaginationDTO<Category> pageRequest = basePagination.paginate(page, perPage);
        return new PaginationDTO<>(pageRequest.getData(), pageRequest.getPagination());
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
    }
}
