package com.makeupstore.services.auth;

import com.makeupstore.dtos.authdtos.GetAuthUser;
import com.makeupstore.dtos.authdtos.LoginUserDto;
import com.makeupstore.dtos.authdtos.SignupUserDto;
import com.makeupstore.enums.ERole;
import com.makeupstore.exceptions.AlreadyExistsException;
import com.makeupstore.models.RoleEntity;
import com.makeupstore.models.UserEntity;
import com.makeupstore.repositories.RoleRepository;
import com.makeupstore.repositories.UserRepository;
import com.makeupstore.security.jwt.JwtUtils;
import com.makeupstore.security.user.AppUserDetails;
import com.makeupstore.services.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    public UserEntity singup(SignupUserDto user) {
        return Optional.of(user).filter(u -> !userRepository.existsByEmail(user.getEmail()))
                .map(u -> {
                    Set<RoleEntity> roles = new HashSet<>();
                    ERole roleEnum;

                    try {
                        roleEnum = ERole.valueOf(user.getRole().toUpperCase()); // Convierte a mayúsculas
                    } catch (IllegalArgumentException e) {
                        throw new RuntimeException("Role not found"); // Manejo de errores
                    }

                    RoleEntity role = roleRepository.findByName(roleEnum)
                            .orElseThrow(() -> new RuntimeException("Role not found"));

                    roles.add(role);

                    UserEntity newUser = new UserEntity();
                    newUser.setName(user.getName());
                    newUser.setEmail(user.getEmail());
                    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    newUser.setRoles(roles);

                    userRepository.save(newUser);

                    newUser.setPassword("");
                    return newUser;
                }).orElseThrow(() -> new AlreadyExistsException("User already exists"));
    }

    @Override
    public GetAuthUser login(LoginUserDto user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        //JwtResponse jwtResponse = new JwtResponse(userDetails.getId(), jwt);

        //Get the user info
        UserEntity foundUser = userService.getUserById(userDetails.getId());

        //Create a new authUser to send it to the user
        GetAuthUser authUser = new GetAuthUser();
        authUser.setId(foundUser.getId());
        authUser.setName(foundUser.getName());
        authUser.setEmail(foundUser.getEmail());

        //Transform the roles into a set of strings
        Set<String> userRoles = foundUser.getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toSet());
        authUser.setRoles(userRoles);
        authUser.setToken(jwt);

        return authUser;
    }

    @Override
    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }
}
