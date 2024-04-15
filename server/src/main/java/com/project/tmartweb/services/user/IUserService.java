package com.project.tmartweb.services.user;

import com.project.tmartweb.models.dtos.UserDTO;
import com.project.tmartweb.models.entities.User;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface IUserService extends IBaseService<User, UserDTO, UUID> {
    String Login(String username, String password);

    User getByUserName(String userName);
}
