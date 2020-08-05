package com.ibatask.usersender.service.impl;

import com.ibatask.usersender.entity.UserEntity;
import com.ibatask.usersender.repository.UserRepository;
import com.ibatask.usersender.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(new UserEntity());
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
