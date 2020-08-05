package com.ibatask.usersender.autorun;

import com.ibatask.usersender.entity.AddressEntity;
import com.ibatask.usersender.entity.PhoneEntity;
import com.ibatask.usersender.entity.UserEntity;
import com.ibatask.usersender.service.AddressService;
import com.ibatask.usersender.service.PhoneService;
import com.ibatask.usersender.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AutoRun implements CommandLineRunner {

    private final UserService userService;
    private final AddressService addressService;
    private final PhoneService phoneService;

    public AutoRun(UserService userService, AddressService addressService, PhoneService phoneService) {
        this.userService = userService;
        this.addressService = addressService;
        this.phoneService = phoneService;
    }

    @Override
    public void run(String... args) throws Exception {
        saveSomeData();
    }

    private void saveSomeData() {

        AddressEntity address1 = AddressEntity.builder()
                .street("Mirze Feteli")
                .houseNumber(21L)
                .build();
        addressService.saveAddress(address1);

        AddressEntity address2 = AddressEntity.builder()
                .street("Qurban Qurbanov")
                .houseNumber(97L)
                .build();
        addressService.saveAddress(address2);

        AddressEntity address3 = AddressEntity.builder()
                .street("Eliaga Shixlinski")
                .houseNumber(115L)
                .build();
        addressService.saveAddress(address3);

        Set<AddressEntity> addresses1 = new HashSet<>();
        Set<AddressEntity> addresses2 = new HashSet<>();
        Set<AddressEntity> addresses3 = new HashSet<>();
        addresses1.add(address1);
        addresses2.add(address2);
        addresses3.add(address3);

        PhoneEntity phone1 = PhoneEntity.builder()
                .phoneNumber("+182726123213")
                .build();

        PhoneEntity phone2 = PhoneEntity.builder()
                .phoneNumber("+122732123213")
                .build();

        PhoneEntity phone3 = PhoneEntity.builder()
                .phoneNumber("+1484324234322")
                .build();

        Set<PhoneEntity> phones1 = new HashSet<>();
        Set<PhoneEntity> phones2 = new HashSet<>();
        Set<PhoneEntity> phones3 = new HashSet<>();
        phones1.add(phone1);
        phones2.add(phone2);
        phones3.add(phone3);

        UserEntity user1 = UserEntity.builder()
                .firstName("Agabala")
                .lastName("Mammadov")
                .addresses(addresses1)
                .phoneEntityNumbers(phones1)
                .build();
        userService.saveUser(user1);

        UserEntity user2 = UserEntity.builder()
                .firstName("Hesenaga")
                .lastName("Altinbeyov")
                .addresses(addresses2)
                .phoneEntityNumbers(phones2)
                .build();
        userService.saveUser(user2);

        UserEntity user3 = UserEntity.builder()
                .firstName("Gulaga")
                .lastName("Mammadyarov")
                .addresses(addresses3)
                .phoneEntityNumbers(phones3)
                .build();
        userService.saveUser(user3);

        phone1.setUserEntity(user1);
        phone2.setUserEntity(user2);
        phone3.setUserEntity(user3);

        phoneService.savePhone(phone1);
        phoneService.savePhone(phone2);
        phoneService.savePhone(phone3);
    }
}
