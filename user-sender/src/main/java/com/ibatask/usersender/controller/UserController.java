package com.ibatask.usersender.controller;

import com.ibatask.usersender.entity.UserEntity;
import com.ibatask.usersender.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<UserEntity> userEntities() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/{id}")
    public UserEntity userById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
