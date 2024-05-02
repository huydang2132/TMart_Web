package com.project.tmartweb.application.repositories;

import com.project.tmartweb.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByTitle(String title);

    Page<Product> findAllByCategory_IdAndDeleted(UUID id, boolean deleted, Pageable pageable);

    Page<Product> findAllByDeleted(boolean deleted, Pageable pageable);

    List<Product> findAllByDeleted(boolean deleted, Sort sort);

    List<Product> findAllByCategory_Id(UUID id);
}
