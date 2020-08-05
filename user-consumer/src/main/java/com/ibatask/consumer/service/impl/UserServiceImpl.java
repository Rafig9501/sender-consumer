package com.ibatask.consumer.service.impl;

import com.ibatask.consumer.database.entity.AddressEntity;
import com.ibatask.consumer.database.entity.PhoneEntity;
import com.ibatask.consumer.database.entity.UserEntity;
import com.ibatask.consumer.database.repository.UserRepository;
import com.ibatask.consumer.dto.UserDto;
import com.ibatask.consumer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserById(Long id) {
        try {
            Optional<UserEntity> userInfoEntity = userRepository.findById(id);
            return userInfoEntity.orElseGet(UserEntity::new);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new UserEntity();
        }
    }

    @Override
    public List<UserEntity> saveUserList(List<UserDto> userDtos) {
        try {
            return userRepository.saveAll(userDtos.stream().map(u -> UserEntity.builder()
                    .id(u.getId())
                    .firstName(u.getFirstName())
                    .lastName(u.getLastName())
                    .addresses(u.getAddresses().stream().map(a -> AddressEntity.builder()
                            .id(a.getId())
                            .houseNumber(a.getHouseNumber())
                            .street(a.getStreet()).build()).collect(Collectors.toSet()))
                    .phoneEntityNumbers(u.getPhoneEntityNumbers().stream().map(p -> PhoneEntity.builder()
                            .id(p.getId())
                            .phoneNumber(p.getPhoneNumber()).build()).collect(Collectors.toSet())).build())
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<UserEntity> getUserList() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ArrayList<>();
        }
    }
}