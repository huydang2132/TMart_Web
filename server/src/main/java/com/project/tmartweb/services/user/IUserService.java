package com.project.tmartweb.services.user;

import com.project.tmartweb.domain.dtos.UserDTO;
import com.project.tmartweb.domain.dtos.UserLoginDTO;
import com.project.tmartweb.domain.entities.User;
import com.project.tmartweb.services.base.IBaseService;

import java.util.UUID;

public interface IUserService extends IBaseService<User, UserDTO, UUID> {
    String Login(UserLoginDTO userLoginDTO);

    User getByUserName(String userName);

    User getByToken(String token);
}
