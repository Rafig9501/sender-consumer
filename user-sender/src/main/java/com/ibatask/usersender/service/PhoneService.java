package com.ibatask.usersender.service;

import com.ibatask.usersender.entity.PhoneEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhoneService {

    List<PhoneEntity> getAllPhones();

    PhoneEntity getPhoneById(Long id);

    PhoneEntity savePhone(PhoneEntity phone);
}
