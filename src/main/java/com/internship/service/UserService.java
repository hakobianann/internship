package com.internship.service;

import com.internship.persistence.entity.UserEntity;
import com.internship.persistence.repository.UserRepository;
import com.internship.service.criteria.SearchCriteria;
import com.internship.service.dto.UserDto;
import com.internship.service.model.QueryResponseWrapper;
import com.internship.service.model.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto updateUserData(Long id, UserDto dto) throws Exception {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("user not found"));
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getStatus() != null) {
            user.setStatus(dto.getStatus());
        }
        user = userRepository.save(user);
        return UserDto.mapEntityToDo(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto createUser(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setStatus("ACTIVE");
        //TODO Change this part after oAuth session and use Bcrypt
        user.setPassHash("123456");

        user = userRepository.save(user);
        return UserDto.mapEntityToDo(user);
    }

    public UserDto getUser(Long id) throws Exception {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("user not found"));
        return UserDto.mapEntityToDo(user);
    }

    public List<UserDto> getUsers(String status) {
        List<UserEntity> users = userRepository.findAllUsers(status);

        return users.stream().map(UserDto::mapEntityToDo).collect(Collectors.toList());
    }

    public QueryResponseWrapper<UserWrapper> getUsers(SearchCriteria criteria) {
        Page<UserWrapper> content = userRepository.findAllWithPagination(criteria.composePageRequest());

        return new QueryResponseWrapper<>(content.getTotalElements(), content.getContent());
    }
}