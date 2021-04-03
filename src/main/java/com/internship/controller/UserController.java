package com.internship.controller;

import com.internship.security.config.session.SessionUser;
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

import static com.internship.security.config.session.SessionUser.SESSION_USER_KEY;

@RestController
@RequestMapping("users")
@SessionAttributes(SESSION_USER_KEY)
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

    @PostMapping("/registration")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto) throws Exception {
        if (dto.getFirstName() == null) {
            throw new Exception("First name is required");
        }
        if (dto.getLastName() == null) {
            throw new Exception("Last name is required");
        }

        if (dto.getUsername() == null) {
            throw new Exception("Username is required");
        }

        if (dto.getPassword() == null) {
            throw new Exception("Password is required");
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

    // This is just an example of how to get user session user data
    // You can use '@ModelAttribute(SESSION_USER_KEY) SessionUser sessionUser' part in any api call where its needed
    @GetMapping("/session")
    public ResponseEntity<SessionUser> getSessionUser(@ModelAttribute(SESSION_USER_KEY) SessionUser sessionUser) {
        return ResponseEntity.ok(sessionUser);
    }
}