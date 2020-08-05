package com.ibatask.consumer.service;

import com.ibatask.consumer.database.entity.PhoneEntity;
import com.ibatask.consumer.dto.PhoneDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhoneService {

    List<PhoneEntity> savePhoneList(List<PhoneDto> users);
}
