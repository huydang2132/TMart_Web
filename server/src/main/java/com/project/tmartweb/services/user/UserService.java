package com.project.tmartweb.services.user;

import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.jwt.JwtTokenUtil;
import com.project.tmartweb.models.dtos.UserDTO;
import com.project.tmartweb.models.entities.Role;
import com.project.tmartweb.models.entities.User;
import com.project.tmartweb.repositories.UserRepository;
import com.project.tmartweb.services.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String Login(String username, String password) {
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new NotFoundException("Tên đăng nhập hoặc mật khẩu không tồn tại", "User or password not found");
        }
        UsernamePasswordAuthenticationToken userToken =
                new UsernamePasswordAuthenticationToken(
                        username, password, user.get().getAuthorities()
                );
        authenticationManager.authenticate(userToken);
        return jwtTokenUtil.generateToken(user.get());
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("Người dùng không tồn tại", "User not found"));
    }

    @Override
    public User insert(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number is already exists");
        }
        User user = mapper.map(userDTO, User.class);
        Role role = roleService.getById(userDTO.getRoleId());
        String encoderPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encoderPassword);
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
        return userRepository.findAll(Sort.by("createdAt").descending());
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(UUID id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Người dùng này không tồn tại", "User not found"));
    }
}
