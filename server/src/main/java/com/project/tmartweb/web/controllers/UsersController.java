package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.UserDTO;
import com.project.tmartweb.domain.dtos.UserLoginDTO;
import com.project.tmartweb.domain.entities.User;
import com.project.tmartweb.domain.enums.RoleId;
import com.project.tmartweb.helpers.GenerateValue;
import com.project.tmartweb.responses.AuthTokenResponse;
import com.project.tmartweb.services.user.IUserService;
import com.project.tmartweb.utils.LocalizationUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UsersController {
    private final IUserService userService;
    private final LocalizationUtils localizationUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        userDTO.setUserName(GenerateValue.generateUsername());
        userDTO.setRoleId(RoleId.USER);
        var res = userService.insert(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("")
    public ResponseEntity<?> insertUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        var res = userService.insert(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO,
            HttpServletRequest request
    ) {
        String token = userService.Login(userLoginDTO);
        return ResponseEntity.status(200).body(
                new AuthTokenResponse(localizationUtils.getLocalizeMessage("user.login.login_success")
                        , token)
        );
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        var result = userService.getAll(page, perPage);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        var result = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<?> getByToken(@PathVariable String token) {
        var result = userService.getByToken(token);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUserName(@PathVariable String username) {
        var result = userService.getByUserName(username);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        User user = userService.getById(id);
        userService.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userDTO));
    }
}
