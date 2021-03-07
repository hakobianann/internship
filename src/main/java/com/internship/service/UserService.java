package com.internship.service;

import com.internship.persistence.entity.UserEntity;
import com.internship.persistence.repository.UserRepository;
import com.internship.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto updateUserData(Long id, UserDto dto) {
        List<UserEntity> users = userRepository.findAll();
        Optional<UserEntity> userOptional = users.stream().filter(userEntity -> userEntity.getId().equals(id)).findAny();
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            return null;
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto createUser(UserDto dto) {
        UserDto user = new UserDto();
        user.setName(dto.getName());
        users.add(user);

        return user;
    }

    public UserDto getUser(Long id) {
        List<UserDto> users = getUserData(null);
        Optional<UserDto> user = users.stream().filter(userDto -> userDto.getId().equals(id)).findAny();
        return user.orElse(null);

    }

    public List<UserDto> getUserData(String name) {
        if (!StringUtils.isEmpty(name)) {
            return users.stream().filter(user -> user.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        }
        return users;
    }
}