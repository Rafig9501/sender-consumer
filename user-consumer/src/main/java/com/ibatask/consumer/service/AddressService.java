package com.ibatask.consumer.service;

import com.ibatask.consumer.database.entity.AddressEntity;
import com.ibatask.consumer.dto.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    List<AddressEntity> saveAddresses(List<AddressDto> addressDtos);
}
