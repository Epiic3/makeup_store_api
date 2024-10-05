package com.makeupstore.services.auth;

import com.makeupstore.dtos.authdtos.GetAuthUser;
import com.makeupstore.dtos.authdtos.LoginUserDto;
import com.makeupstore.dtos.authdtos.SignupUserDto;
import com.makeupstore.models.UserEntity;

public interface IAuthService {
    UserEntity singup(SignupUserDto user);
    GetAuthUser login(LoginUserDto user);
    UserEntity getAuthenticatedUser();
}
