package com.makeupstore.security.user;

import com.makeupstore.exceptions.ResourceNotFoundException;
import com.makeupstore.models.UserEntity;
import com.makeupstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = Optional.ofNullable(userRepository.findByEmail(email)).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return AppUserDetails.buildUserDetails(user);
    }
}
