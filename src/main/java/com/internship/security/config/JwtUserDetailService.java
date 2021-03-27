package com.internship.security.config;

import com.internship.persistence.entity.UserEntity;
import com.internship.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailService implements UserDetailsService {

    final UserRepository userRepository;

    @Autowired
    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = getUserEntityByUsername(username.toLowerCase()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with name=%s was not found", username)));

        return new User(username, user.getPassHash(), new ArrayList<>());
    }

    private Optional<UserEntity> getUserEntityByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return Optional.ofNullable(user);
    }
}