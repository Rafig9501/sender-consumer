package com.ibatask.usersender.service.impl;

import com.ibatask.usersender.entity.PhoneEntity;
import com.ibatask.usersender.entity.UserEntity;
import com.ibatask.usersender.repository.PhoneRepository;
import com.ibatask.usersender.service.PhoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<PhoneEntity> getAllPhones() {
        return phoneRepository.findAll();
    }

    public PhoneEntity getPhoneById(Long id) {
        return phoneRepository.findById(id).orElse(new PhoneEntity());
    }

    public PhoneEntity savePhone(PhoneEntity phone) {
        return phoneRepository.save(phone);
    }
}
