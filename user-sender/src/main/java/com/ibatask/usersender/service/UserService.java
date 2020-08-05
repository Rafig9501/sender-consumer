package com.ibatask.usersender.service;

import com.ibatask.usersender.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity getUserById(Long id);

    UserEntity saveUser(UserEntity user);
}
