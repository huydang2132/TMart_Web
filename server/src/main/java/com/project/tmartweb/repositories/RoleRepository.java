package com.project.tmartweb.repositories;

import com.project.tmartweb.enums.RoleId;
import com.project.tmartweb.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, RoleId> {
}
