package com.ibatask.usersender.service;

import com.ibatask.usersender.entity.AddressEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    List<AddressEntity> getAllAddresses();

    AddressEntity getAddressById(Long id);

    AddressEntity saveAddress(AddressEntity address);
}
