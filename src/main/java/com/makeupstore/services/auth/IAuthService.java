package com.makeupstore.services.auth;

import com.makeupstore.models.UserEntity;

public interface IAuthService {
    UserEntity singup(UserEntity user);
}
