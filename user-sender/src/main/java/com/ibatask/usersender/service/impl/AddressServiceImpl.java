package com.ibatask.usersender.service.impl;

import com.ibatask.usersender.entity.AddressEntity;
import com.ibatask.usersender.repository.AddressRepository;
import com.ibatask.usersender.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    public AddressEntity getAddressById(Long id) {
        return addressRepository.findById(id).orElse(new AddressEntity());
    }

    public AddressEntity saveAddress(AddressEntity address) {
        return addressRepository.save(address);
    }
}
