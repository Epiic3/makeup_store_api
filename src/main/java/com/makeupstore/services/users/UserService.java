package com.makeupstore.services.users;

import com.makeupstore.dtos.userdtos.UpdateUserDto;
import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.UserEntity;
import com.makeupstore.repositories.RoleRepository;
import com.makeupstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserEntity updateUser(UpdateUserDto updateUserDto, Long userId) {
        UserEntity existingUser = getUserById(userId);

        existingUser.setName(updateUserDto.getName());
        existingUser.setPassword(updateUserDto.getPassword());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User not found");
        });
    }
}
