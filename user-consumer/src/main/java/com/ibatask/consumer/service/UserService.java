package com.ibatask.consumer.service;

import com.ibatask.consumer.database.entity.UserEntity;
import com.ibatask.consumer.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserEntity getUserById(Long id);

    List<UserEntity> saveUserList(List<UserDto> users);

    List<UserEntity> getUserList();
}
