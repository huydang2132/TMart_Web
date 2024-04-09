package com.project.tmartweb.services.imp;

import com.project.tmartweb.repositories.UserRepository;
import com.project.tmartweb.services.IUserService;
import com.project.tmartweb.models.entities.Role;
import com.project.tmartweb.exceptions.DataNotFoundException;
import com.project.tmartweb.models.dtos.UserDTO;
import com.project.tmartweb.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RoleService roleService;

    @Override
    public String Login(String username, String password) {

        return null;
    }

    @Override
    public User insert(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number is already exists");
        }
        User user = mapper.map(userDTO, User.class);
        Role role = roleService.getById(userDTO.getRoleId());
        user.setRole(role);
        user.setCreatedBy(userDTO.getCreatedBy());
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, UserDTO userDTO) {
        User user = getById(id);
        Role role = roleService.getById(userDTO.getRoleId());
        user.setRole(role);
        user.setUpdatedBy(userDTO.getUpdatedBy());
        mapper.map(userDTO, user);
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(UUID id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException("user not found"));
    }
}
