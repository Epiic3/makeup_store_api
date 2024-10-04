package com.makeupstore.services.users;

import com.makeupstore.dtos.userdtos.UpdateUserDto;
import com.makeupstore.models.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity updateUser(UpdateUserDto updateUserDto, Long userId);
    void deleteUser(Long id);
}
