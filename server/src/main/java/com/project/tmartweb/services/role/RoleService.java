package com.project.tmartweb.services.role;

import com.project.tmartweb.enums.RoleId;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.models.entities.Role;
import com.project.tmartweb.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll(Sort.by("createdAt").descending());
    }

    @Override
    public Optional<Role> findById(RoleId id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getById(RoleId id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Vai trò không tồn tại!", "Role not found"));
    }
}
