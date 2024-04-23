package com.project.tmartweb.repositories;

import com.project.tmartweb.domain.entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    Page<Feedback> findAllByProductId(UUID id, Pageable pageable);

    List<Feedback> findAllByProductId(UUID id);
}
