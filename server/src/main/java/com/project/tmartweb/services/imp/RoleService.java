package com.project.tmartweb.services.imp;

import com.project.tmartweb.repositories.RoleRepository;
import com.project.tmartweb.services.IRoleService;
import com.project.tmartweb.models.entities.Role;
import com.project.tmartweb.exceptions.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role getById(String id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException("Role not found"));
    }
}
