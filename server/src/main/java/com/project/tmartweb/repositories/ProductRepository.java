package com.project.tmartweb.repositories;

import com.project.tmartweb.models.entities.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByTitle(String title);


    List<Product> findAllByDeletedOrderByCreatedAtDesc(boolean deleted);
}
