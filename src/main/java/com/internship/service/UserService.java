package com.internship.service;

import com.internship.service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    List<UserDto> users = new ArrayList<>();

    public UserDto updateUserData(Long id, UserDto dto) {
        Optional<UserDto> user = users.stream().filter(userDto -> userDto.getId().equals(id)).findAny();
        if (user.isPresent()) {
            UserDto userDto = user.get();
            userDto.setName(dto.getName());
            return userDto;
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        users.removeIf(userDto -> userDto.getId().equals(id));
    }

    public UserDto createUser(UserDto dto) {
        UserDto user = new UserDto();
        user.setId((long) users.size() + 1);
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

    public void initUser() {
        users.add(new UserDto(1L, "Robin Scherbatksy"));
        users.add((new UserDto(2L, "Barney Stinson")));
        users.add((new UserDto(3L, "Ted Mosby")));
        users.add(new UserDto(4L, "Michael Scott"));
    }
}