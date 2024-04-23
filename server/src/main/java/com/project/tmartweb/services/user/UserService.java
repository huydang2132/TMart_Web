package com.project.tmartweb.services.user;

import com.project.tmartweb.domain.dtos.UserDTO;
import com.project.tmartweb.domain.dtos.UserLoginDTO;
import com.project.tmartweb.domain.entities.Role;
import com.project.tmartweb.domain.entities.Token;
import com.project.tmartweb.domain.entities.User;
import com.project.tmartweb.domain.paginate.PaginationDTO;
import com.project.tmartweb.exceptions.ConflictException;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.jwt.JwtTokenUtil;
import com.project.tmartweb.repositories.TokenRepository;
import com.project.tmartweb.repositories.UserRepository;
import com.project.tmartweb.services.role.RoleService;
import com.project.tmartweb.services.token.ITokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    private final ITokenService tokenService;
    private final TokenRepository tokenRepository;

    @Override
    public String Login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUserName();
        String password = userLoginDTO.getPassword();
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new NotFoundException("Tên đăng nhập hoặc mật khẩu không tồn tại", "User or password not found");
        }
        UsernamePasswordAuthenticationToken userToken =
                new UsernamePasswordAuthenticationToken(
                        username, password, user.get().getAuthorities()
                );
        authenticationManager.authenticate(userToken);
        String tokenString = jwtTokenUtil.generateToken(user.get());
        Token token = new Token();
        token.setUser(user.get());
        token.setToken(tokenString);
        token.setExpirationDate(jwtTokenUtil.getExpirationDate(tokenString));
        token.setTokenType("Authentication");
        tokenRepository.save(token);
        return tokenString;
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("Người dùng không tồn tại", "User not found"));
    }

    @Override
    public User getByToken(String token) {
        Token tokenModel = tokenService.getById(token);
        if (tokenModel.getExpired()) {
            throw new NotFoundException("Token đã hết hạn", "Token expired");
        }
        return tokenModel.getUser();
    }

    @Override
    public User insert(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        if (phoneNumber != null && userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ConflictException("Số điện thoại dã tồn tại", "Phone number is already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ConflictException("Email đã tồn tại", "Email is already exists");
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
    public PaginationDTO<User> getAll(Integer page, Integer perPage) {
        return null;
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
