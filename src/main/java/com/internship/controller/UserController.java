package com.internship.controller;

import com.internship.service.UserService;
import com.internship.service.criteria.SearchCriteria;
import com.internship.service.dto.UserDto;
import com.internship.service.model.QueryResponseWrapper;
import com.internship.service.model.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDto> getUsers(@RequestParam(value = "status") String status) {
        return userService.getUsers(status);
    }

    @GetMapping("/with-pagination")
    public QueryResponseWrapper<UserWrapper> getUsers(SearchCriteria criteria) {
        return userService.getUsers(criteria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable() Long id) throws Exception {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) throws Exception {
        if (dto.getFirstName() == null) {
            throw new Exception("First name is required");
        }
        if (dto.getLastName() == null) {
            throw new Exception("Last name is required");
        }
        UserDto user = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @RequestBody UserDto dto) throws Exception {
        UserDto user = userService.updateUserData(id, dto);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void addUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }
}