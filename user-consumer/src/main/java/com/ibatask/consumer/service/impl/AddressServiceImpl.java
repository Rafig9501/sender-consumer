package com.ibatask.consumer.service.impl;

import com.ibatask.consumer.database.entity.AddressEntity;
import com.ibatask.consumer.database.repository.AddressRepository;
import com.ibatask.consumer.dto.AddressDto;
import com.ibatask.consumer.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressEntity> saveAddresses(List<AddressDto> addressDtos) {
        try {
            return addressRepository.saveAll(addressDtos.stream().map(a -> AddressEntity.builder()
                    .id(a.getId())
                    .street(a.getStreet())
                    .houseNumber(a.getHouseNumber())
                    .build()).collect(Collectors.toList()));
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ArrayList<>();
        }
    }
}
