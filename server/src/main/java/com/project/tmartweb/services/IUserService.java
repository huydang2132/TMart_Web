package com.project.tmartweb.services;

import com.project.tmartweb.services.base.IBaseService;
import com.project.tmartweb.models.dtos.UserDTO;
import com.project.tmartweb.models.entities.User;

import java.util.UUID;

public interface IUserService extends IBaseService<User, UserDTO, UUID> {
    String Login(String username, String password);
}
