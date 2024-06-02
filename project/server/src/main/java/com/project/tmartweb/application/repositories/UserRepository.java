package com.project.tmartweb.application.repositories;

import com.project.tmartweb.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query("SELECT u from User u WHERE u.userName = :userName or u.email = :userName or u.phoneNumber = :userName")
    Optional<User> findByUserName(String userName);

    List<User> findAllByDeleted(Boolean deleted, Sort sort);

    Page<User> findAllByDeleted(Boolean deleted, Pageable pageable);
}
