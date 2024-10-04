package com.makeupstore.services.auth;

import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.models.UserEntity;
import com.makeupstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;

    @Override
    public UserEntity singup(UserEntity user) {
        return Optional.of(user).filter(u -> !userRepository.existsByEmail(user.getEmail()))
                .map(u -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setName(user.getName());
                    newUser.setEmail(user.getEmail());
                    newUser.setPassword(user.getPassword());
                    newUser.setRoles(user.getRoles());

                    return userRepository.save(newUser);
                }).orElseThrow(() -> new AlreadyExistsException("User already exists"));
    }
}
