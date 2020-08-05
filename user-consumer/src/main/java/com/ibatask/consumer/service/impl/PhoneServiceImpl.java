package com.ibatask.consumer.service.impl;

import com.ibatask.consumer.database.entity.PhoneEntity;
import com.ibatask.consumer.database.entity.UserEntity;
import com.ibatask.consumer.database.repository.PhoneRepository;
import com.ibatask.consumer.database.repository.UserRepository;
import com.ibatask.consumer.dto.PhoneDto;
import com.ibatask.consumer.service.PhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository, UserRepository userRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<PhoneEntity> savePhoneList(List<PhoneDto> phones) {
        try {
            List<PhoneEntity> phoneList = phones.stream().map(p -> PhoneEntity.builder()
                    .id(p.getId())
                    .phoneNumber(p.getPhoneNumber())
                    .build()).collect(Collectors.toList());

            phoneRepository.saveAll(phones.stream().map(p -> PhoneEntity.builder()
                    .id(p.getId())
                    .phoneNumber(p.getPhoneNumber())
                    .userEntity(UserEntity.builder()
                            .id(p.getUserDto().getId())
                            .firstName(p.getUserDto().getFirstName())
                            .lastName(p.getUserDto().getLastName())
                            .phoneEntityNumbers(phoneList.stream().filter(ph -> p.getId().equals(ph.getId()))
                                    .collect(Collectors.toSet())).build())
                    .build()).collect(Collectors.toList()));
            return null;
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ArrayList<>();
        }
    }
}