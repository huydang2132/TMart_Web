package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.enums.RoleId;
import com.project.tmartweb.services.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/roles")
public class RolesController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> getAllRoles() {
        var result = roleService.getAll(null, null);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable RoleId id) {
        var result = roleService.getById(id);
        return ResponseEntity.ok(result);
    }
}
